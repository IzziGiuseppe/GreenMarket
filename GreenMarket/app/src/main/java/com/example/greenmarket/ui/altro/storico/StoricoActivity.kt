package com.example.greenmarket.ui.altro.storico

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R
import com.example.greenmarket.ui.altro.storico.dettaglio_scontrini.DettaglioScontriniActivity

class StoricoActivity : AppCompatActivity() {

    val storicoViewModel: StoricoViewModel by viewModels()

    @OptIn(UnstableApi::class)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storico)

        val adapter = StoricoListAdapter() { item ->
            item.data.let { storicoViewModel.readScontrinoDettagliato(it) }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv_scontrini)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        storicoViewModel.readScontrini()
        storicoViewModel.lista_scontrini.observe(this, Observer { scontrino ->
            adapter.setData(scontrino)
        })

        storicoViewModel.scontrino.observe(this) { scontrino ->
            scontrino?.let {
                startProdotto(it.data, it.prodotti, it.totale, it.codiceSconto, it.valoreSconto)
            }
            //storicoViewModel.resetScontrino()
        }
    }

    @OptIn(UnstableApi::class)
    private fun startProdotto(data: String, prodotti: Map<String?, List<Float>?>, totale: Float, codiceSconto: String, valoreSconto: String) {
        val intent = Intent(this, DettaglioScontriniActivity::class.java)
        intent.putExtra("data", data)
        // Passaggio della Map
        val bundle = Bundle()
        for ((key, value) in prodotti) {
            if (value != null) {
                bundle.putFloatArray(key, value.toFloatArray())
            }
        }
        intent.putExtra("map_bundle", bundle)
        intent.putExtra("codice_sconto", codiceSconto)
        Log.d("AAAAAAAAAAAAA", codiceSconto)
        intent.putExtra("valore_sconto", valoreSconto)
        Log.d("BBBBBBBBBBBBB", valoreSconto)
        intent.putExtra("totale", totale)
        startActivity(intent)
    }
}
