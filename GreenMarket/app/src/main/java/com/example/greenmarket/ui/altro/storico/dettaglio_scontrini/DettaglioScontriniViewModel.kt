package com.example.greenmarket.ui.altro.storico.dettaglio_scontrini

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.greenmarket.db.model.ComposizioneScontrini

class DettaglioScontriniViewModel : ViewModel() {

    private var _listaProdDettScontr = MutableLiveData(arrayOf<ComposizioneScontrini>())
    val listaProdDettScontr: MutableLiveData<Array<ComposizioneScontrini>>
        get() = _listaProdDettScontr

    private var _codiceSconto = MutableLiveData<String>().apply {
        value = "-"
    }
    val codiceSconto: LiveData<String> = _codiceSconto

    fun readListaSpesa() {
        val x = arrayOf(
            ComposizioneScontrini(1, "Mele", 3f),
            ComposizioneScontrini(1, "Pere", 2f),
            ComposizioneScontrini(1, "Banane", 2f),
            ComposizioneScontrini(1, "Arance", 3f),
            ComposizioneScontrini(1, "Noci", 2f),
            ComposizioneScontrini(1, "Cicorie", 2f),
            ComposizioneScontrini(1, "Ananas", 3f),
            ComposizioneScontrini(1, "Menta", 2f),
            ComposizioneScontrini(1, "Pasta", 2f),
            ComposizioneScontrini(1, "Pomodoro", 3f),
            ComposizioneScontrini(1, "Mozzarella", 2f),
            ComposizioneScontrini(1, "Carota", 2f),
            ComposizioneScontrini(1, "Melanzane", 3f),
            ComposizioneScontrini(1, "Zucchine", 2f),
            ComposizioneScontrini(1, "Cipolle", 2f),
            ComposizioneScontrini(1, "Porro", 3f),
            ComposizioneScontrini(1, "Aglio", 2f),
            ComposizioneScontrini(1, "Peperoni", 2f)
        )
        _listaProdDettScontr.value = x
    }

    fun readCodiceSconto() {
        val x = "bfewvfce"
        _codiceSconto.value = x
    }

}