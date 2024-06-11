package com.example.greenmarket.ui.ricerca

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Locale

class RicercaViewModel(application: Application) : AndroidViewModel(application) {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _prodotto = MutableLiveData<ProdottoModel>()
    val prodotto: LiveData<ProdottoModel> = _prodotto

    private val _listaProdotti = MutableLiveData<List<ProdottoModel>>()
    val listaProdotti: LiveData<List<ProdottoModel>> = _listaProdotti

    fun readProdottoDettagliato(nome: String) {
        db.collection("products").document(nome).get()
            .addOnSuccessListener { document ->
                _prodotto.value = document.toObject(ProdottoModel::class.java)
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Error getting product details", exception)
            }
    }

    fun prodottiByNome(nome: String) {
        if (nome.isBlank()) {
            Toast.makeText(getApplication(), "Per favore inserire un prodotto!", Toast.LENGTH_SHORT).show()
        } else {
            val prodInput = nome.trim().capitalize(Locale.ROOT)
            db.collection("products").whereEqualTo("nome", prodInput).get()
                .addOnSuccessListener { documents ->
                    val products = documents.toObjects(ProdottoModel::class.java)
                    if (products.isEmpty()) {
                        Toast.makeText(getApplication(), "Non ci sono prodotti con il nome inserito!", Toast.LENGTH_SHORT).show()
                    } else {
                        _listaProdotti.value = products
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("Firebase", "Error getting products", exception)
                }
        }
    }

    fun readAllProdotti() {
        db.collection("products").get()
            .addOnSuccessListener { documents ->
                _listaProdotti.value = documents.toObjects(ProdottoModel::class.java)
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Error getting products", exception)
            }
    }
}
