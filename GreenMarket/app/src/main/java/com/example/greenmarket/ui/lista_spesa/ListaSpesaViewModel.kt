package com.example.greenmarket.ui.lista_spesa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListaSpesaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Funziona"
    }
    val text: LiveData<String> = _text
}