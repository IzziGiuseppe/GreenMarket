package com.example.greenmarket.ui.altro

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AltroViewModel(application: Application) : AndroidViewModel(application) {

    private val _menuItems = MutableLiveData<List<AltroMenu>>().apply {
        value = listOf(
            AltroMenu("Profilo Utente"),
            AltroMenu("Storico"),
            AltroMenu("Chi siamo"),
            AltroMenu("Classifica prodotti più acquistati"),
            AltroMenu("Invita un amico"),
            AltroMenu("Termini e condizioni d'uso"),
            AltroMenu("Contatta l'assistenza"),
            AltroMenu("Effettua una donazione")
        )
    }
    val menuItems: LiveData<List<AltroMenu>> = _menuItems

}