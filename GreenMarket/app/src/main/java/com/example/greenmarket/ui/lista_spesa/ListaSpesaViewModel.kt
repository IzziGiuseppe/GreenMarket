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
            ComposizioneScontrini(1, "Banane", 2f)
        )
        _listaSpesa.value = x
    }

    fun deleteListaSpesa() {
        _listaSpesa.value = arrayOf()
    }

}