package com.example.greenmarket.ui.altro.storico

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greenmarket.db.model.Scontrino

class StoricoViewModel(application: Application): AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "Storico scontrini"
    }
    val text: LiveData<String> = _text

    private var _listaScontrini = MutableLiveData(arrayOf<Scontrino>())
    val listaScontrini: MutableLiveData<Array<Scontrino>>
        get() = _listaScontrini

    fun readScontrini() {
        val x = arrayOf(
            Scontrino(1, "05/06/2024", "Utente", "", true),
            Scontrino(2, "06/06/2024", "Utente", "", true),
            Scontrino(3, "07/06/2024", "Utente", "", true)
        )
        _listaScontrini.value = x
    }

}