package com.example.greenmarket.ui.ricettario

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greenmarket.db.GMDatabase
import com.example.greenmarket.db.model.ProdottiInRicette
import com.example.greenmarket.db.model.Ricetta

class RicettarioViewModel(application: Application) : AndroidViewModel(application) {
    private val db: GMDatabase = GMDatabase.getInstance(application)

    private var _ricetta = MutableLiveData(ProdottiInRicette("", ""))
    val ricetta: LiveData<ProdottiInRicette>
        get() = _ricetta

    private var _ricettaDettagliata = MutableLiveData(Ricetta("", "", ""))
    val ricettaDettagliata: LiveData<Ricetta>
        get() = _ricettaDettagliata

    private var _listaRicette = MutableLiveData(arrayOf<ProdottiInRicette>())
    val listaRicette: MutableLiveData<Array<ProdottiInRicette>>
        get() = _listaRicette

    fun readRicetta(nome: String){
        //_ricetta.value = db.RicettaDao().getRicettaByNome(nome)
    }

    fun readRicettaDettagliata(nome: String){
        _ricettaDettagliata.value = db.RicettaDao().getRicettaByNome(nome)
    }

    fun ricetteByProdotto(prod: String){
        if (prod.isBlank()) {
            Toast.makeText(getApplication(), "Per favore inserire un prodotto!", Toast.LENGTH_SHORT).show()
        }
        else {
            val prodInput = prod.trim()[0].uppercaseChar() + prod.trim().substring(1).lowercase()
            val x = db.ProdottiInRicetteDao().getProdottiInRicetteByProdotto(prodInput)
            if (x.isEmpty()) {
                Toast.makeText(getApplication(), "Non ci sono ricette con l'ingrediente selezionato!", Toast.LENGTH_SHORT).show()
            }
            else {
                _listaRicette.value = x
            }
        }
    }

    fun readAllRicette(){
        val x = db.ProdottiInRicetteDao().getAll()
        _listaRicette.value = x
    }

    fun insert(vararg ricetta: Ricetta){
        db.RicettaDao().insert(*ricetta)
    }

    fun deleteAllRicette(){
        db.RicettaDao().deleteAllRicette()
    }
}