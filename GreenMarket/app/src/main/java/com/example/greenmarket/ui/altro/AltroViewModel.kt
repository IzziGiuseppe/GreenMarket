package com.example.greenmarket.ui.altro

import android.app.Application
import android.widget.Toast
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
            AltroMenu("Effettua una donazione")
        )
    }
    val menuItems: LiveData<List<AltroMenu>> = _menuItems

    fun sceltaListener(position: Int) {
        when (position) {
            0 -> profiloUtente()
            1 -> storico()
            2 -> chiSiamo()
            3 -> stats()
            4 -> invito()
            5 -> terminiCondizioni()
            6 -> donazione()
            else ->
                Toast.makeText(getApplication(), "No action", Toast.LENGTH_SHORT).show()
        }
    }

    private fun profiloUtente() {
        Toast.makeText(getApplication(), "Profilo utente da implementare", Toast.LENGTH_LONG).show()
    }

    private fun storico() {
        Toast.makeText(getApplication(), "Storico da implementare", Toast.LENGTH_LONG).show()
    }

    private fun chiSiamo() {
        Toast.makeText(getApplication(), "Chi siamo da implementare", Toast.LENGTH_LONG).show()
    }

    private fun stats() {
        Toast.makeText(getApplication(), "Statistiche da implementare", Toast.LENGTH_LONG).show()
    }

    private fun invito() {
        Toast.makeText(getApplication(), "Invito da implementare", Toast.LENGTH_LONG).show()
    }

    private fun terminiCondizioni() {
        Toast.makeText(getApplication(), "Termini e condizioni da implementare", Toast.LENGTH_LONG).show()
    }

    private fun donazione() {
        Toast.makeText(getApplication(), "Donazioni da implementare", Toast.LENGTH_LONG).show()
    }

}