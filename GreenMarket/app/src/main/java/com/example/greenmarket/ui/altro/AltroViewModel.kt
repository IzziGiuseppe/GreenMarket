package com.example.greenmarket.ui.altro

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greenmarket.R

class AltroViewModel(application: Application) : AndroidViewModel(application) {

    private val _menuItems = MutableLiveData<List<AltroMenu>>().apply {
        value = listOf(
            AltroMenu("Profilo Utente", R.mipmap.ic_user_profile),
            AltroMenu("Storico", R.drawable.storico),
            AltroMenu("Chi siamo", R.drawable.chi_siamo),
            AltroMenu("Statistiche", R.drawable.stats),
            AltroMenu("Invita un amico", R.drawable.invita),
            AltroMenu("Termini e condizioni d'uso", R.drawable.term_cond),
            AltroMenu("Contatta l'assistenza", R.drawable.assistenza),
            AltroMenu("Effettua una donazione", R.drawable.donazione)
        )
    }
    val menuItems: LiveData<List<AltroMenu>> = _menuItems

}