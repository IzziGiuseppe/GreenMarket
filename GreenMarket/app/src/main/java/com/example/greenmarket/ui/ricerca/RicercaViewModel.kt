package com.example.greenmarket.ui.ricerca

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.greenmarket.db.GMDatabase
import com.example.greenmarket.db.model.ProdottiInRicette
import com.example.greenmarket.db.model.Prodotto

class RicercaViewModel(application: Application) : AndroidViewModel(application) {

    private val db: GMDatabase = GMDatabase.getInstance(application)

    private val _text = MutableLiveData<String>().apply {
        value = "Ricerca"
    }
    val text: LiveData<String> = _text

    private var _prodotto = MutableLiveData(Prodotto("", "", 0f, "", "", ))
    val prodotto: LiveData<Prodotto>
        get() = _prodotto

    private var _listaProdotti = MutableLiveData(arrayOf<Prodotto>())
    val listaProdotti: MutableLiveData<Array<Prodotto>>
        get() = _listaProdotti

    fun readProdottoDettagliato(nome: String) {
        _prodotto.value = db.ProdottoDao().getProdottoDettagliatoByNome(nome)
    }

    fun prodottiByNome(nome: String) {
        if (nome.isBlank()) {
            Toast.makeText(getApplication(), "Per favore inserire un prodotto!", Toast.LENGTH_SHORT).show()
        }
        else {
            val prodInput = nome.trim()[0].uppercaseChar() + nome.trim().substring(1).lowercase()
            val x = db.ProdottoDao().getProdottoByNome(prodInput)
            if (x.isEmpty()) {
                Toast.makeText(getApplication(), "Non ci sono prodotti con il nome inserito!", Toast.LENGTH_SHORT).show()
            }
            else {
                _listaProdotti.value = x
            }
        }
    }

    fun readAllProdotti() {
        val x = db.ProdottoDao().getAll()
        _listaProdotti.value = x
    }
}