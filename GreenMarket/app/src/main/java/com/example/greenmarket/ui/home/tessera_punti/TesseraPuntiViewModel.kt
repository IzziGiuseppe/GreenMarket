package com.example.greenmarket.ui.home.tessera_punti

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greenmarket.db.model.CodiceSconto
import com.example.greenmarket.ui.ricerca.ProdottoModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.math.BigDecimal
import java.math.RoundingMode

class TesseraPuntiViewModel(application: Application): AndroidViewModel(application)  {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser

    private val _tessera_punti = MutableLiveData<TesseraPuntiModel>()
    val tessera_punti: LiveData<TesseraPuntiModel> = _tessera_punti

    private val _saldo = MutableLiveData<Float?>()
    val saldo: MutableLiveData<Float?> = _saldo

    private val _punti = MutableLiveData<Int?>()
    val punti: MutableLiveData<Int?> = _punti

    private var _listaCodiciSconto= MutableLiveData(listOf<String>())
    val listaCodiciSconto: MutableLiveData<List<String>>
        get() = _listaCodiciSconto

    fun readTesseraPunti() {
        currentUser?.let {
            db.collection("users").document(it.uid).collection("pointCard").document("wallet").get()
                .addOnSuccessListener { document ->
                    _tessera_punti.value = document.toObject(TesseraPuntiModel::class.java)
                    _saldo.value = _tessera_punti.value?.saldo

                    _punti.value = _tessera_punti.value?.punti
                }
                .addOnFailureListener { exception ->
                    Log.e("Firebase", "Error getting point card details", exception)
                }
        }

    }

    fun convertiSaldo() {
        if(saldo.value!! >= 10f){
            _punti.value = _punti.value?.plus((_saldo.value?.div(10) ?: 0).toInt())
            _saldo.value = _saldo.value?.rem(10f)
            _saldo.value =
                _saldo.value?.let { BigDecimal(it.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toFloat() }

            //update punti
            val updates = hashMapOf(
                "saldo" to _saldo.value,
                "punti" to _punti.value
            )
            currentUser?.let {
                db.collection("users").document(it.uid).collection("pointCard").document("wallet").set(updates)
                    .addOnSuccessListener {
                        Toast.makeText(getApplication(), "Conversione effettuata con successo", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener{
                        Toast.makeText(getApplication(), "Errore durante la conversione", Toast.LENGTH_SHORT).show()
                    }
            }
        }else{
            Toast.makeText(getApplication(), "Saldo insufficiente", Toast.LENGTH_SHORT).show()
        }
    }

    fun riscattaCodiceSconto(){
        var listaCS = mutableListOf<String>()
        if(_punti.value!! >= 5){
            val codici_sconto_generati = _punti.value?.div(5)
            _punti.value = _punti.value?.rem(5)
            for(i in 1..codici_sconto_generati!!){
                listaCS.add(generateRandomString())
            }
            for (i in _listaCodiciSconto.value!!) {
                listaCS.add(i)
            }
            //update codici sconto
            val updatesCS = hashMapOf(
                "codici sconto" to listaCS
            )
            //update punti
            val updatesTP = hashMapOf(
                "saldo" to _saldo.value,
                "punti" to _punti.value
            )
            db.collection("users").document(currentUser?.uid!!).collection("pointCard").document("coupons").update(updatesCS as Map<String, Any>)
                .addOnSuccessListener {
                    db.collection("users").document(currentUser.uid).collection("pointCard").document("wallet").update(updatesTP as Map<String, Any>)

                }
            _listaCodiciSconto.value = listaCS
        }
    }

    fun readCodiciSconto() {
        currentUser?.let {
            db.collection("users").document(it.uid).collection("pointCard").document("coupons")
                .get()
                .addOnSuccessListener { document ->
                    _listaCodiciSconto.value = document.get("codici sconto") as? List<String>
                }
        }
    }

    //funzione che crea in modo random i codici sconto
    private fun generateRandomString(length: Int = 5): String {
        val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }
}