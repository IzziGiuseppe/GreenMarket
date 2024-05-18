package com.example.greenmarket.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greenmarket.db.GMDatabase
import com.example.greenmarket.db.model.Prodotto

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "Giusè"
    }
    val text: LiveData<String> = _text

    private val db:GMDatabase = GMDatabase.getInstance(application)

    private var _prodotto = MutableLiveData(Prodotto("Mela", "", 0f, "", ""))
    val prodotto: LiveData<Prodotto>
        get() = _prodotto

    private var _listaProdotti = MutableLiveData(arrayOf<Prodotto>())
    val listaProdotti: MutableLiveData<Array<Prodotto>>
        get() = _listaProdotti

    fun readProdotto(nome: String){
        _prodotto.value = db.ProdottoDao().getProdottoByNome(nome)    }

    fun readAllStudents(){
        val x = db.ProdottoDao().getAll()
        _listaProdotti.value = x
    }

    fun insert(vararg prodotto: Prodotto){
        db.ProdottoDao().insert(*prodotto)
    }

    fun deleteAllProdotti(){
        db.ProdottoDao().deleteAllProdotti()
    }


}