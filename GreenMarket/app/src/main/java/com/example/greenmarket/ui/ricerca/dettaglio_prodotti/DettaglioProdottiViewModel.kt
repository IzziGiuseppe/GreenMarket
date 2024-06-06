package com.example.greenmarket.ui.ricerca.dettaglio_prodotti

import android.app.Application
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

}