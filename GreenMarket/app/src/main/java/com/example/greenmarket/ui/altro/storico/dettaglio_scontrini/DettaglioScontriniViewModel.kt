package com.example.greenmarket.ui.altro.storico.dettaglio_scontrini

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.greenmarket.db.model.ComposizioneScontrini
import com.example.greenmarket.ui.lista_spesa.ProdottoInListaModel
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Timestamp

class DettaglioScontriniViewModel : ViewModel() {

    private var _data_scontrino = MutableLiveData<String>().apply {
        value = "1970-01-01 00:00:00"
    }
    val data_scontrino: LiveData<String> = _data_scontrino

    private var _totale_scontrino = MutableLiveData<Float>().apply {
        value = 0f
    }
    val totale_scontrino: LiveData<Float> = _totale_scontrino

    private var _lista_prodotti_scontrino = MutableLiveData(listOf<ProdottoInListaModel>())
    val lista_prodotti_scontrino: MutableLiveData<List<ProdottoInListaModel>>
        get() = _lista_prodotti_scontrino

    private var _codiceSconto = MutableLiveData<String>().apply {
        value = "-"
    }
    val codiceSconto: LiveData<String> = _codiceSconto

    fun setData(data: String?) {
        _data_scontrino.value = data
    }

    fun setTotale(totale: Float) {
        _totale_scontrino.value = totale
    }

    fun setListaProdotti(lista: List<ProdottoInListaModel>) {
        _lista_prodotti_scontrino.value = lista
    }
    fun readListaProdottiInScontrino() {

    }

    fun readCodiceSconto() {
        val x = "bfewvfce"
        _codiceSconto.value = x
    }

    fun listaProdotti(map: Map<String, List<Float>>) : List<ProdottoInListaModel>{
        val listaProdotti = mutableListOf<ProdottoInListaModel>()
        map.forEach { (key, value) ->
            val prodotto =
                ProdottoInListaModel(
                    key, value.get(0) ?: 0.5f, value.get(1) ?: 0f, value.get(2)
                    ?: 0f)
            listaProdotti.add(prodotto)
        }
        return listaProdotti
    }

}