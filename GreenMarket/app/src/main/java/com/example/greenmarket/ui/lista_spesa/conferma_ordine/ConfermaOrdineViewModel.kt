package com.example.greenmarket.ui.lista_spesa.conferma_ordine

import android.app.Application
import androidx.annotation.OptIn
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.greenmarket.ui.home.tessera_punti.TesseraPuntiModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.Format

class ConfermaOrdineViewModel(application: Application): AndroidViewModel(application){
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser

    private val _tessera_punti = MutableLiveData<TesseraPuntiModel>()
    val tessera_punti: LiveData<TesseraPuntiModel> = _tessera_punti

    private var _prezzo_totale = MutableLiveData<String>().apply {
        value = "€0.00"
    }
    val prezzo_totale: LiveData<String> = _prezzo_totale

    private var _listaCodiciSconto= MutableLiveData(listOf<String>())
    val listaCodiciSconto: MutableLiveData<List<String>>
        get() = _listaCodiciSconto

    @OptIn(UnstableApi::class)
    fun setPrezzoTotale(prezzoTotale: String) {
        _prezzo_totale.value = prezzoTotale
    }

    @OptIn(UnstableApi::class)
    fun aggiornaSaldo() {
        currentUser?.let {
            db.collection("users").document(it.uid).collection("pointCard").document("wallet").get()
                .addOnSuccessListener {document ->
                    _tessera_punti.value = document.toObject(TesseraPuntiModel::class.java)
                    val saldo = _prezzo_totale.value?.toFloat()?.plus(_tessera_punti.value?.saldo!!)

                    val updates = hashMapOf(
                        "punti" to _tessera_punti.value?.punti,
                        "saldo" to saldo
                    )
                    db.collection("users").document(it.uid).collection("pointCard").document("wallet")
                        .set(updates)
                }
        }

    }

    fun readCodiciSconto() {
        currentUser?.let {
            db.collection("users").document(it.uid).collection("pointCard").document("coupons")
                .get()
                .addOnSuccessListener { document ->
                    val vect = document.get("codici sconto") as? List<String>
                    if (vect != null) {
                        if (vect.isEmpty()) {
                            _listaCodiciSconto.value = listOf("Non ci sono codici sconto a disposizione")
                        }
                        else {
                            val mutableVect = vect.toMutableList()
                            mutableVect.add(0, "-")
                            _listaCodiciSconto.value = mutableVect
                        }

                    }
                }
        }
    }

    fun deleteListaSpesa() {
        //Svuotiamo la lista della spesa nel database
        val prodotti: Map<String?, List<Float>?> = emptyMap()
        //Creazione lista della spesa associata all'utente
        val updates = hashMapOf(
            "data" to null,
            "valido" to false,
            "prodotti" to prodotti
        )

        currentUser?.let {
            db.collection("users").document(it.uid).collection("historical")
                .document("shoppingList").update(updates)
        }
    }
}