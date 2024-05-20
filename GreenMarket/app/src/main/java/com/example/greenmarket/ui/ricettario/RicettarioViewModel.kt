package com.example.greenmarket.ui.ricettario

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.greenmarket.db.GMDatabase
import com.example.greenmarket.db.model.Prodotto
import com.example.greenmarket.db.model.Ricetta

class RicettarioViewModel(application: Application) : AndroidViewModel(application) {
    private val db: GMDatabase = GMDatabase.getInstance(application)

    private var _ricetta = MutableLiveData(Ricetta("", "", ""))
    val ricetta: LiveData<Ricetta>
        get() = _ricetta

    private var _listaRicette = MutableLiveData(arrayOf<Ricetta>())
    val listaRicette: MutableLiveData<Array<Ricetta>>
        get() = _listaRicette

    fun readRicetta(nome: String){
        _ricetta.value = db.RicettaDao().getRicettaByNome(nome)
    }

    fun readAllRicette(){
        val x = db.RicettaDao().getAll()
        _listaRicette.value = x
    }

    fun insert(vararg ricetta: Ricetta){
        db.RicettaDao().insert(*ricetta)
    }

    fun deleteAllRicette(){
        db.RicettaDao().deleteAllRicette()
    }
}