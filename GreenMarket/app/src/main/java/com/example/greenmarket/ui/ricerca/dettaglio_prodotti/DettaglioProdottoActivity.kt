package com.example.greenmarket.ui.ricerca.dettaglio_prodotti

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.greenmarket.R

class DettaglioProdottoActivity : AppCompatActivity() {

    private val dettaglioProdottiViewModel: DettaglioProdottiViewModel by viewModels()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_dettaglio_prodotto)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val nome: TextView = findViewById(R.id.nome_prezzo_prodotto)
        val descrizione: TextView = findViewById(R.id.descrizione_prodotto)
        val foto: ImageView = findViewById(R.id.foto_prodotto)

        val nomeProdotto = intent.getStringExtra("nome_prezzo_prodotto")
        val descrizioneProdotto = intent.getStringExtra("descrizione_prodotto")
        val fotoProdotto = intent.getStringExtra("foto_prodotto")

        if (nomeProdotto != null) {
            dettaglioProdottiViewModel.setNome(nomeProdotto)
        }
        dettaglioProdottiViewModel.nome_prodotto.observe(this) {
            nome.text = it
        }

        if (descrizioneProdotto != null) {
            dettaglioProdottiViewModel.setDescrizione(descrizioneProdotto)
        }
        dettaglioProdottiViewModel.descrizione_prodotto.observe(this) {
            descrizione.text = it
        }

        if (!fotoProdotto.isNullOrEmpty()) {
            dettaglioProdottiViewModel.setFoto(fotoProdotto)
        }
        dettaglioProdottiViewModel.foto_prodotto.observe(this) {
            Glide.with(this)
                .load(fotoProdotto)
                .into(foto)
        }
    }
}