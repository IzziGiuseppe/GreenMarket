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
import com.example.greenmarket.ui.ricerca.RicercaViewModel
import com.example.greenmarket.ui.ricerca.dettaglio_prodotti.DettaglioProdottoActivity

class StatsActivity : AppCompatActivity() {

    private val statsViewModel: StatsViewModel by viewModels()
    private val ricercaViewModel: RicercaViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_stats)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val text = findViewById<TextView>(R.id.stats_txt)

        statsViewModel.text.observe(this) {
            text.text = it
        }

        val adapter = StatsListAdapter() {
                currentProdotto ->
            run {
                Toast.makeText(this, currentProdotto.nome, Toast.LENGTH_SHORT).show()
                val prodotto = currentProdotto.nome
                var nome = ""
                var descrizione = ""
                var prezzo = 0f
                var foto = ""
                ricercaViewModel.readProdottoDettagliato(prodotto)
                ricercaViewModel.prodotto.observe(this) {
                    nome = it.nome.toString()
                    descrizione = it.descrizione.toString()
                    prezzo = it.prezzo!!
                    foto = it.foto.toString()
                }
                startProdotto(nome, descrizione, prezzo, foto)
            }
        }
        val recyclerView = findViewById<RecyclerView>(R.id.rv_prodotti_stats)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        /*val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val dividerDrawable = ColorDrawable(ContextCompat.getColor(this, R.color.black))
        dividerItemDecoration.setDrawable(dividerDrawable)
        recyclerView.addItemDecoration(dividerItemDecoration)*/

        statsViewModel.readProdotti()
        statsViewModel.listaProdStats.observe(this, Observer {
                prodotto -> adapter.setData(prodotto)
        })
    }

    fun startProdotto(nome: String, descrizione: String, prezzo: Float, foto: String) {
        val intent = Intent(this, DettaglioProdottoActivity::class.java)
        intent.putExtra("nome_prezzo_prodotto", "$nome \n$$prezzo")
        intent.putExtra("descrizione_prodotto", descrizione)
        intent.putExtra("foto_prodotto", foto)
        startActivity(intent)
    }
}