package com.example.greenmarket.ui.altro.statistiche

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greenmarket.db.GMDatabase
import com.example.greenmarket.db.model.Prodotto
import com.example.greenmarket.db.model.Scontrino

class StatsViewModel(application: Application) : AndroidViewModel(application) {

    private val db: GMDatabase = GMDatabase.getInstance(application)

    private val _text = MutableLiveData<String>().apply {
        value = "Classifica prodotti più acquistati"
    }
    val text: LiveData<String> = _text

    private var _listaProdStats = MutableLiveData(arrayOf<Prodotto>())
    val listaProdStats: MutableLiveData<Array<Prodotto>>
        get() = _listaProdStats

    fun readProdotti() {
        val x = db.ProdottoDao().getAll()
        _listaProdStats.value = x
    }

}