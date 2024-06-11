package com.example.greenmarket.ui.lista_spesa

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.greenmarket.ui.ricerca.ProdottoModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Timestamp

class ListaSpesaViewModel : ViewModel() {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser


    private val _text = MutableLiveData<String>().apply {
        value = "Lista spesa"
    }
    val text: LiveData<String> = _text

    private val _prezzo_totale_view = MutableLiveData<String>().apply {
        value = "Totale: "
    }
    val prezzo_totale_view: LiveData<String> = _prezzo_totale_view

    private var _listaSpesa = MutableLiveData<ListaDellaSpesaModel>()
    val listaSpesa: MutableLiveData<ListaDellaSpesaModel>
        get() = _listaSpesa

    private var _listaProdotti = MutableLiveData<List<ProdottoInListaModel>>()
    val listaProdotti: MutableLiveData<List<ProdottoInListaModel>>
        get() = _listaProdotti

    private val _prodotto = MutableLiveData<ProdottoModel>()
    val prodotto: LiveData<ProdottoModel> = _prodotto

    fun readProdottoDettagliato(nome: String) {
        db.collection("products").document(nome).get()
            .addOnSuccessListener { document ->
                _prodotto.value = document.toObject(ProdottoModel::class.java)
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Error getting product details", exception)
            }
    }

    fun readListaSpesa() {
        currentUser?.let {
            db.collection("users").document(it.uid).collection("historical").document("shoppingList").get()
                .addOnSuccessListener { documents ->
                    _listaSpesa.value = documents.toObject(ListaDellaSpesaModel::class.java)
                    _listaProdotti.value = _listaSpesa.value?.let { it1 -> listaProdotti(it1.prodotti) }
                }
                .addOnFailureListener { exception ->
                    Log.e("Firebase", "Error getting products", exception)
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
                .addOnSuccessListener {
                    readListaSpesa()
                }
                .addOnFailureListener { exception ->
                    Log.e("Firebase", "Error deleting shopping list", exception)
                }
        }
    }

    private fun listaProdotti(map: Map<String?, List<Float>?>) : List<ProdottoInListaModel>{
        val listaProdotti = mutableListOf<ProdottoInListaModel>()
        map.forEach { (key, value) ->
            val prodotto = key?.let { ProdottoInListaModel(it, value?.get(0) ?: 0.5f, value?.get(1) ?: 0f, value?.get(2) ?: 0f) }
            if (prodotto != null) {
                listaProdotti.add(prodotto)
            }
        }
        return listaProdotti
    }

    fun deleteProdByNome(nome: String) {
        currentUser?.uid?.let {
            db.collection("users").document(it).collection("historical").document("shoppingList").get()
                .addOnSuccessListener { documents ->
                    val listaSpesa = documents.toObject(ListaDellaSpesaModel::class.java)
                    val prodotti = listaSpesa?.prodotti?.toMutableMap()
                    prodotti?.remove(nome)

                    val updates = hashMapOf(
                        "data" to null,
                        "valido" to false,
                        "prodotti" to prodotti
                    )
                    db.collection("users").document(it).collection("historical")
                        .document("shoppingList").update(updates)
                        .addOnSuccessListener {
                            readListaSpesa()
                        }
                        .addOnFailureListener { exception ->
                            Log.e("Firebase", "Error", exception)
                        }
                }
        }
    }

    fun readPrezzoTotale(){
        var totale = 0.0
        if (listaProdotti.value != null) {
            for( i in listaProdotti.value!!){
                totale += i.prezzoTotale
            }
        }

        _prezzo_totale_view.value = "Totale: $totale"
    }
}