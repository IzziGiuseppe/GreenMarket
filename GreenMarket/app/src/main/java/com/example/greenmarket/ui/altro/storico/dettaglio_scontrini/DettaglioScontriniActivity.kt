package com.example.greenmarket.ui.altro.storico.dettaglio_scontrini

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R

class DettaglioScontriniActivity : AppCompatActivity() {

    private val dettaglioScontriniViewModel: DettaglioScontriniViewModel by viewModels()

    @OptIn(UnstableApi::class)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dettaglio_scontrini)

        val cs: TextView = findViewById(R.id.codice_sconto_dett_scontr)
        val valCS: TextView = findViewById(R.id.valore_codice_sconto)
        val data: TextView = findViewById(R.id.data_dettaglio_scontrino)
        val totale: TextView = findViewById(R.id.prezzo_totale)

        val dataScontrino = intent.getStringExtra("data")
        val totaleScontrino = intent.getFloatExtra("totale", 0f)
        // Recupero mappa
        val bundle = intent.getBundleExtra("map_bundle")
        val prodottiScontrino: Map<String, List<Float>> = bundle?.keySet()?.associateWith { key ->
            bundle.getFloatArray(key)?.toList() ?: emptyList()
        } ?: emptyMap()
        val codiceScontoScontrino = intent.getStringExtra("codice_sconto")
        val valoreCodiceScontoScontrino = intent.getStringExtra("valore_sconto")


        if (dataScontrino != null) {
            dettaglioScontriniViewModel.setData(dataScontrino)
        }

        dettaglioScontriniViewModel.data_scontrino.observe(this) { dataValue ->
            data.text = dataValue
        }

        dettaglioScontriniViewModel.setTotale(totaleScontrino)
        dettaglioScontriniViewModel.totale_scontrino.observe(this) { totaleValue ->
            totale.text = totaleValue.toString()
        }


        if (prodottiScontrino.isNotEmpty()) {
            dettaglioScontriniViewModel.setListaProdotti(dettaglioScontriniViewModel.listaProdotti(prodottiScontrino))
        }

        if(codiceScontoScontrino != null) {
            dettaglioScontriniViewModel.setCodiceSconto(codiceScontoScontrino)
        }

        dettaglioScontriniViewModel.codice_sconto.observe(this) { codiceScontoValue ->
            if (codiceScontoScontrino == "-") {
                cs.text = "Codice sconto: -"
                valCS.text = "-"
            } else {
                cs.text = "Codice sconto: $codiceScontoValue"
            }
        }

        if(valoreCodiceScontoScontrino != null) {
            dettaglioScontriniViewModel.setValoreCodiceSconto(valoreCodiceScontoScontrino)
        }

        dettaglioScontriniViewModel.valore_codice_sconto.observe(this) { valoreCodiceScontoValue ->
            valCS.text = valoreCodiceScontoValue
        }


        val adapter = DettaglioScontriniListAdapter()
        val rv = findViewById<RecyclerView>(R.id.rv_dettaglio_scontrini)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        dettaglioScontriniViewModel.lista_prodotti_scontrino.observe(this) { prodotti ->
            adapter.setData(prodotti)
        }


    }
}
