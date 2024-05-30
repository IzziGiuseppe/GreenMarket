package com.example.greenmarket.ui.altro.storico

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StoricoViewModel(application: Application): AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "Storico scontrini"
    }
    val text: LiveData<String> = _text

}