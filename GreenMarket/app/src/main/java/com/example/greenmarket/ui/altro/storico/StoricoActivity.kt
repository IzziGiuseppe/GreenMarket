package com.example.greenmarket.ui.altro.storico

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.util.UnstableApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.ui.internet.InternetTest
import com.example.greenmarket.R
import com.example.greenmarket.ui.altro.storico.dettaglio_scontrini.DettaglioScontriniActivity

class StoricoActivity : AppCompatActivity() {

    private val storicoViewModel: StoricoViewModel by viewModels()

    @OptIn(UnstableApi::class)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storico)

        val iT = InternetTest()

        val adapter = StoricoListAdapter() { item ->
            if (iT.isInternetAvailable(this)) {
                item.data.let { storicoViewModel.readScontrinoDettagliato(it) }
            } else {
                iT.toast(this)
            }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv_scontrini)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        storicoViewModel.readScontrini()
        storicoViewModel.lista_scontrini.observe(this) { scontrino ->
            adapter.setData(scontrino)
        }

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
        intent.putExtra("valore_sconto", valoreSconto)
        intent.putExtra("totale", totale)
        startActivity(intent)
    }
}
