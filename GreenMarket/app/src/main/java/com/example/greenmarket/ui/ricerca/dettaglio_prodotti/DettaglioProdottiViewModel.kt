package com.example.greenmarket.ui.ricerca.dettaglio_prodotti

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DettaglioProdottiViewModel(application: Application): AndroidViewModel(application) {

    private var _nome_prodotto = MutableLiveData<String>().apply {
        value = "Nome prodotto"
    }
    val nome_prodotto: LiveData<String> = _nome_prodotto

    private var _descrizione_prodotto = MutableLiveData<String>().apply {
        value = "Descrizione prodotto"
    }
    val descrizione_prodotto: LiveData<String> = _descrizione_prodotto

    private var _foto_prodotto = MutableLiveData<String>().apply {
        value = "Foto prodotto"
    }
    val foto_prodotto: LiveData<String> = _foto_prodotto

    private var _prezzo_prodotto = MutableLiveData<Float>().apply {
        value = 0f
    }
    val prezzo_prodotto: LiveData<Float> = _prezzo_prodotto

    private var _uM_prodotto = MutableLiveData<String>().apply {
        value = "Unità di misura prodotto"
    }
    val uM_prodotto: LiveData<String> = _uM_prodotto

    private var _quantita_prodotto = MutableLiveData<Int>().apply {
        value = 1
    }
    val quantita_prodotto: LiveData<Int> = _quantita_prodotto

    fun setNome(nome: String) {
        _nome_prodotto.value = nome
    }

    fun setDescrizione(descrizione: String) {
        _descrizione_prodotto.value = descrizione
    }

    fun setFoto(foto: String) {
        _foto_prodotto.value = foto
    }

    fun setPrezzo(prezzo: Float) {
        _prezzo_prodotto.value = prezzo
    }

    fun setUM(uM: String) {
        _uM_prodotto.value = uM
    }

    fun incrementaQuantita() {
        if(quantita_prodotto.value!! < 100){
            _quantita_prodotto.value = _quantita_prodotto.value?.plus(1)
        }else{
            Toast.makeText(getApplication(), "La quantità non può superare 100 kg", Toast.LENGTH_SHORT).show()
        }

    }

    fun decrementaQuantita() {
        if(quantita_prodotto.value!! > 0){
            _quantita_prodotto.value = _quantita_prodotto.value?.minus(1)
        }else{
            Toast.makeText(getApplication(), "La quantità non può essere negativa", Toast.LENGTH_SHORT).show()
        }

    }

}