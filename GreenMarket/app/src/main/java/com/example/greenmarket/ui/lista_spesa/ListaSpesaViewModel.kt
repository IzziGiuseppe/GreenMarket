package com.example.greenmarket.ui.lista_spesa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.greenmarket.db.model.ComposizioneScontrini
import com.example.greenmarket.db.model.Prodotto

class ListaSpesaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Lista spesa"
    }
    val text: LiveData<String> = _text

    private var _listaSpesa = MutableLiveData(arrayOf<ComposizioneScontrini>())
    val listaSpesa: MutableLiveData<Array<ComposizioneScontrini>>
        get() = _listaSpesa

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
        _listaSpesa.value = x
    }

    fun deleteListaSpesa() {
        _listaSpesa.value = arrayOf()
    }

}