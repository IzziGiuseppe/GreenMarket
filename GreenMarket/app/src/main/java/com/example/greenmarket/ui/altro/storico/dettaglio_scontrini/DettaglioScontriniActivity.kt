package com.example.greenmarket.ui.altro.storico.dettaglio_scontrini

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R

class DettaglioScontriniActivity : AppCompatActivity() {

    private val dettaglioScontriniViewModel: DettaglioScontriniViewModel by viewModels()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_dettaglio_scontrini)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val cs: TextView = findViewById(R.id.codice_sconto_dett_scontr)
        val valCS: TextView = findViewById(R.id.valore_codice_sconto)
        val codiceSconto = intent.getStringExtra("codice_sconto")
        if (codiceSconto == "") {
            cs.text = "Codice sconto: -"
            valCS.text = "-"
        }
        else {
            cs.text = "Codice sconto: $codiceSconto"
        }

        val data: TextView = findViewById(R.id.data_dettaglio_scontrino)
        data.text = intent.getStringExtra("data_scontrino")

        val adapter = DettaglioScontriniListAdapter()
        val rv = findViewById<RecyclerView>(R.id.rv_dettaglio_scontrini)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val dividerDrawable = ColorDrawable(ContextCompat.getColor(this, R.color.black))
        dividerItemDecoration.setDrawable(dividerDrawable)
        rv.addItemDecoration(dividerItemDecoration)

        //dettaglioScontriniViewModel.readCodiceSconto()
        dettaglioScontriniViewModel.readListaSpesa()

        dettaglioScontriniViewModel.listaProdDettScontr.observe(this, Observer {
            prodDettScontr -> adapter.setData(prodDettScontr)
        })

    }
}