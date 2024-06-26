package com.example.greenmarket.ui.altro.storico.dettaglio_scontrini

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.greenmarket.db.model.ProdottoInListaModel

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

    private var _codice_sconto = MutableLiveData<String>().apply {
        value = "-"
    }
    val codice_sconto: LiveData<String> = _codice_sconto

    private var _valore_codice_sconto = MutableLiveData<String>().apply {
        value = "€0.00"
    }
    val valore_codice_sconto: LiveData<String> = _valore_codice_sconto

    fun setData(data: String?) {
        _data_scontrino.value = data
    }

    fun setTotale(totale: Float) {
        _totale_scontrino.value = totale
    }

    fun setListaProdotti(lista: List<ProdottoInListaModel>) {
        _lista_prodotti_scontrino.value = lista
    }
    fun setCodiceSconto(codiceScontoScontrino: String) {
        _codice_sconto.value = codiceScontoScontrino
    }

    fun setValoreCodiceSconto(valoreCodiceScontoScontrino: String) {
        _valore_codice_sconto.value = valoreCodiceScontoScontrino
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