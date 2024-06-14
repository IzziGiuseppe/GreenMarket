package com.example.greenmarket.ui.lista_spesa

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.greenmarket.ui.ricerca.ProdottoModel
import com.example.greenmarket.ui.ricerca.dettaglio_prodotti.DettaglioProdottoActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.math.BigDecimal
import java.math.RoundingMode
import java.sql.Timestamp
import kotlin.math.floor

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

    private val _quantita = MutableLiveData<Float>()
    val quantita: LiveData<Float> = _quantita

    fun readProdottoDettagliato(nome: String) {
        db.collection("products").document(nome).get()
            .addOnSuccessListener { document ->
                _prodotto.value = document.toObject(ProdottoModel::class.java)
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Error getting product details", exception)
            }
    }

    fun setQuantita(quantita: Float) {
        _quantita.value = quantita
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
        var totale = BigDecimal("0.0")
        if (listaProdotti.value != null) {
            for( i in listaProdotti.value!!){
                totale = totale.add(BigDecimal(i.prezzoTotale.toString())).setScale(2, RoundingMode.HALF_UP)
            }
        }
        _prezzo_totale_view.value = "Totale: €$totale"
    }

    fun resetProdotto() {
        _prodotto.value = ProdottoModel()
    }
}