package com.example.greenmarket.ui.ricerca

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RicercaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ricerca"
    }
    val text: LiveData<String> = _text
}