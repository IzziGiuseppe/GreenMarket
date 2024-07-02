package com.example.greenmarket.ui.altro.statistiche

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R
import com.example.greenmarket.ui.altro.storico.dettaglio_scontrini.DettaglioScontriniListAdapter
import com.example.greenmarket.ui.ricerca.RicercaViewModel
import com.example.greenmarket.ui.ricerca.dettaglio_prodotti.DettaglioProdottoActivity

class StatsActivity : AppCompatActivity() {

    private val statsViewModel: StatsViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        val text = findViewById<TextView>(R.id.stats_txt)

        statsViewModel.text.observe(this) {
            text.text = it
        }

        val adapter = StatsListAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.rv_prodotti_stats)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        statsViewModel.readProdotti()
        statsViewModel.listaProdStats.observe(this, Observer {
                prodotto -> adapter.setData(prodotto)
        })

    }
}