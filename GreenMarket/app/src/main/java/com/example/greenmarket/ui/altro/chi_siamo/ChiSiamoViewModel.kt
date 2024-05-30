package com.example.greenmarket.ui.altro.chi_siamo

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ChiSiamoViewModel(application: Application) : AndroidViewModel(application) {

    private val _storia_azienda = MutableLiveData<String>().apply {
        value = "Storia azienda"
    }
    val storia_azienda: LiveData<String> = _storia_azienda

    private val _text = MutableLiveData<String>().apply {
        value = testo_storia()
    }
    val text: LiveData<String> = _text

    private val _dove_siamo = MutableLiveData<String>().apply {
        value = "Dove siamo"
    }
    val dove_siamo: LiveData<String> = _dove_siamo

    private val _giuse = MutableLiveData<String>().apply {
        value = "Giuseppe"
    }
    val giuse: LiveData<String> = _giuse

    private val _dom = MutableLiveData<String>().apply {
        value = "Domenico"
    }
    val dom: LiveData<String> = _dom

    fun giuse_action() {
        Toast.makeText(getApplication(), "Giuseppe da implementare", Toast.LENGTH_SHORT).show()
    }

    fun dom_action() {
        Toast.makeText(getApplication(), "Domenico da implementare", Toast.LENGTH_SHORT).show()
    }

    private fun testo_storia() : String {
        return "GreenMarket nasce nel 1965 ad Ancona grazie a Giuseppe Izzi, detto Peppe, un agricoltore originario " +
                "di Montenero di Bisaccia e poi trasferitosi nel capoluogo marchigiano, " +
                "appassionato di prodotti genuini e sostenibili. Il piccolo supermercato si distingue " +
                "subito per la qualità dei suoi prodotti locali, biologici e freschi. Con il tempo, " +
                "GreenMarket diventa un punto di riferimento per la comunità, promuovendo eventi e iniziative " +
                "sostenibili. Oggi, gestito dal nipote e omonimo Giuseppe e dal suo socio in affari Domenico La " +
                "Porta, il supermercato continua a crescere, " +
                "abbracciando l'innovazione. L'idea di un'app per fare la spesa online è nata per offrire " +
                "ai clienti un servizio comodo e moderno, rispondendo così alle esigenze di chi vuole acquistare " +
                "prodotti " +
                "di qualità anche a distanza, permettendo inoltre di ottenere sconti e di creare liste" +
                "della spesa personalizzate"
    }

}