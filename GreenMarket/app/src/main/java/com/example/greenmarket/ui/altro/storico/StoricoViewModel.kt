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
            Scontrino(3, "07/06/2024", "Utente", "", true),
            Scontrino(4, "08/06/2024", "Utente", "", true),
            Scontrino(5, "09/06/2024", "Utente", "", true),
            Scontrino(6, "10/06/2024", "Utente", "", true),
            Scontrino(7, "11/06/2024", "Utente", "", true),
            Scontrino(8, "12/06/2024", "Utente", "", true),
            Scontrino(9, "13/06/2024", "Utente", "", true),
            Scontrino(10, "14/06/2024", "Utente", "", true),
            Scontrino(11, "15/06/2024", "Utente", "", true),
            Scontrino(12, "16/06/2024", "Utente", "", true),
            Scontrino(13, "17/06/2024", "Utente", "", true),
            Scontrino(14, "18/06/2024", "Utente", "", true),
            Scontrino(15, "19/06/2024", "Utente", "", true),
            Scontrino(16, "20/06/2024", "Utente", "", true),
            Scontrino(17, "21/06/2024", "Utente", "", true),
            Scontrino(18, "22/06/2024", "Utente", "", true),
            Scontrino(19, "23/06/2024", "Utente", "", true),
            Scontrino(20, "24/06/2024", "Utente", "", true),
            Scontrino(21, "25/06/2024", "Utente", "", true),
            Scontrino(22, "26/06/2024", "Utente", "", true),
            Scontrino(23, "27/06/2024", "Utente", "", true),
            Scontrino(24, "28/06/2024", "Utente", "", true)
        )
        _listaScontrini.value = x
    }

}