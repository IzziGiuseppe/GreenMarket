package com.example.greenmarket.ui.altro.statistiche

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StatsViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "Prodotti più ordinati"
    }
    val text: LiveData<String> = _text

}