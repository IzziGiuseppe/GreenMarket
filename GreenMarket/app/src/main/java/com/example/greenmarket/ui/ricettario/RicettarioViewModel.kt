package com.example.greenmarket.ui.ricettario

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greenmarket.db.model.RicettaModel
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Locale

class RicettarioViewModel(application: Application) : AndroidViewModel(application) {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var _ricetta = MutableLiveData<RicettaModel>()
    val ricetta: LiveData<RicettaModel> = _ricetta

    private var _listaRicette = MutableLiveData<List<RicettaModel>>()
    val listaRicette: LiveData<List<RicettaModel>> = _listaRicette

    fun readRicetta(nome: String){
        //_ricetta.value = db.RicettaDao().getRicettaByNome(nome)
    }

    fun readRicettaDettagliata(nome: String){
        db.collection("recipes").document(nome).get()
            .addOnSuccessListener { document ->
                _ricetta.value = document.toObject(RicettaModel::class.java)
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Error getting product details", exception)
            }
    }

    fun ricetteByProdotto(prod: String) {
        if (prod.isBlank()) {
            Toast.makeText(getApplication(), "Per favore inserire un prodotto!", Toast.LENGTH_SHORT).show()
        } else {
            val prodottoInput = prod.trim().capitalize(Locale.ROOT)
            db.collection("recipes").whereArrayContains("ingredienti", prodottoInput).get()
                .addOnSuccessListener { documents ->
                    val ricetta = documents.toObjects(RicettaModel::class.java)
                    if (ricetta.isEmpty()) {
                        Toast.makeText(getApplication(), "Non ci sono ricette corrispondenti al prodotto inserito!", Toast.LENGTH_SHORT).show()
                    } else {
                        _listaRicette.value = ricetta
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("Firebase", "Error getting products", exception)
                }
        }
    }

    fun readAllRicette() {
        db.collection("recipes").get()
            .addOnSuccessListener { documents ->
                _listaRicette.value = documents.toObjects(RicettaModel::class.java)
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Error getting products", exception)
            }
    }

    fun resetRicetta() {
        _ricetta.value = RicettaModel()
    }
}