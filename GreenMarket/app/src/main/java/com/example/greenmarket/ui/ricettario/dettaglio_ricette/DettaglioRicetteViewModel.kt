package com.example.greenmarket.ui.ricettario.dettaglio_ricette

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DettaglioRicetteViewModel(application: Application) : AndroidViewModel(application) {

    private var _nome_ricetta = MutableLiveData<String>().apply {
        value = "Nome ricetta"
    }
    val nome_ricetta: LiveData<String> = _nome_ricetta

    private var _descrizione_ricetta = MutableLiveData<String>().apply {
        value = "Descrizione ricetta"
    }
    val descrizione_ricetta: LiveData<String> = _descrizione_ricetta

    private var _foto_ricetta = MutableLiveData<String>().apply {
        value = "Foto ricetta"
    }
    val foto_ricetta: LiveData<String> = _foto_ricetta

    private var _ingredienti_ricetta = MutableLiveData<List<String>>().apply {
        value = listOf("Ingrediente 1", "Ingrediente 2", "Ingrediente 3")
    }
    val ingredienti_ricetta: LiveData<List<String>> = _ingredienti_ricetta

    fun setNome(nome: String) {
        _nome_ricetta.value = nome
    }

    fun setDescrizione(descrizione: String) {
        _descrizione_ricetta.value = descrizione
    }

    fun setFoto(foto: String) {
        _foto_ricetta.value = foto
    }

    fun setIngredienti(ingredienti: List<String>) {
        // TODO
    }
}