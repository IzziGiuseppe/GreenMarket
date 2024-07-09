package com.example.greenmarket.ui.ricerca.dettaglio_prodotti

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.greenmarket.ui.internet.InternetTest
import com.example.greenmarket.R

class DettaglioProdottoActivity : AppCompatActivity() {

    private val dettaglioProdottoViewModel: DettaglioProdottoViewModel by viewModels()
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dettaglio_prodotto)
        val iT = InternetTest()

        //prendo le view dal layout
        val nome: TextView = findViewById(R.id.nome_prodotto)
        val prezzo: TextView = findViewById(R.id.prezzo_prodotto)
        val descrizione: TextView = findViewById(R.id.descrizione_prodotto)
        val foto: ImageView = findViewById(R.id.foto_prodotto)
        val add: ImageView = findViewById(R.id.imageViewAdd)
        val remove: ImageView = findViewById(R.id.imageViewRemove)
        val quantita: TextView = findViewById(R.id.textViewQuantita)
        val addList: Button = findViewById(R.id.buttonAddList)

        //recupero gli elementi passati attraverso il lancio tramite Intent
        val nomeProdotto = intent.getStringExtra("nome_prodotto")
        val prezzoProdotto = intent.getFloatExtra("prezzo_prodotto", 0f)
        val descrizioneProdotto = intent.getStringExtra("descrizione_prodotto")
        val fotoProdotto = intent.getStringExtra("foto_prodotto")
        val quantitaProdotto = intent.getFloatExtra("quantita_prodotto", 0.5f)

        if (intent.getBooleanExtra("to_modify", false)) {
            addList.text = "Modifica quantità"
        }

        if (nomeProdotto != null) {
            dettaglioProdottoViewModel.setNome(nomeProdotto)
        }
        dettaglioProdottoViewModel.nome_prodotto.observe(this) {
            nome.text = it
        }

        dettaglioProdottoViewModel.setPrezzo(prezzoProdotto)
        dettaglioProdottoViewModel.prezzo_prodotto.observe(this) {
            prezzo.text = "€$it"
        }

        if (descrizioneProdotto != null) {
            dettaglioProdottoViewModel.setDescrizione(descrizioneProdotto)
        }
        dettaglioProdottoViewModel.descrizione_prodotto.observe(this) {
            descrizione.text = it
        }

        if (!fotoProdotto.isNullOrEmpty()) {
            dettaglioProdottoViewModel.setFoto(fotoProdotto)
        }
        dettaglioProdottoViewModel.foto_prodotto.observe(this) {
            Glide.with(this)
                .load(fotoProdotto)
                .into(foto)
        }

        add.setOnClickListener{
            dettaglioProdottoViewModel.incrementaQuantita()
        }
        remove.setOnClickListener{
            dettaglioProdottoViewModel.decrementaQuantita()
        }

        dettaglioProdottoViewModel.setQuantita(quantitaProdotto)
        dettaglioProdottoViewModel.quantita_prodotto.observe(this) {
            quantita.text = it.toString()
        }

        addList.setOnClickListener{
            if (iT.isInternetAvailable(this)) {
                dettaglioProdottoViewModel.inserimentoProdottoInListaSpesa()
                finish()
            } else {
                iT.toast(this)
            }
        }

    }
}