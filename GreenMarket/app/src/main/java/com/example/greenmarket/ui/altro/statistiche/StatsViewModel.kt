package com.example.greenmarket.ui.altro.statistiche

import android.app.Application
import androidx.annotation.OptIn
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.greenmarket.db.GMDatabase
import com.example.greenmarket.db.model.Prodotto
import com.example.greenmarket.db.model.Scontrino
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class StatsViewModel(application: Application) : AndroidViewModel(application) {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser

    private val _text = MutableLiveData<String>().apply {
        value = "Classifica prodotti più acquistati"
    }
    val text: LiveData<String> = _text

    private var _listaProdStats = MutableLiveData(mutableListOf<ProdottoInStatsModel>())
    val listaProdStats: MutableLiveData<MutableList<ProdottoInStatsModel>>
        get() = _listaProdStats

    private var _prodottoInStats = MutableLiveData<ProdottoInStatsModel>()
    val prodottoInStats: MutableLiveData<ProdottoInStatsModel>
        get() = _prodottoInStats

    @OptIn(UnstableApi::class)
    fun readProdotti() {
        currentUser?.let {
            db.collection("users").document(it.uid).collection("stats").document("top_selling_products").get()
                .addOnSuccessListener {document->
                    val prodottiMap = document.data
                    if (prodottiMap != null) {
                        val lista = _listaProdStats.value ?: mutableListOf()
                        for((key,value) in prodottiMap){
                            if(value is Number){
                                val valueFloat = value.toFloat()
                                val prodotto = ProdottoInStatsModel(key, valueFloat)
                                lista.add(prodotto)
                            }

                        }
                        val listaOrdinata = lista.sortedByDescending { it.quantitaTot }.toMutableList()
                        _listaProdStats.value = listaOrdinata
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("StatsViewModel", "Errore durante il recupero dei prodotti: ", exception)
                }
        }
    }
}