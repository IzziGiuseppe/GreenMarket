package com.example.greenmarket.ui.ricerca.dettaglio_prodotti

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.greenmarket.R
import com.example.greenmarket.ui.lista_spesa.ListaSpesaViewModel

class DettaglioProdottoActivity : AppCompatActivity() {

    private val dettaglioProdottiViewModel: DettaglioProdottiViewModel by viewModels()
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dettaglio_prodotto)

        val nome: TextView = findViewById(R.id.nome_prodotto)
        val prezzo: TextView = findViewById(R.id.prezzo_prodotto)
        val descrizione: TextView = findViewById(R.id.descrizione_prodotto)
        val foto: ImageView = findViewById(R.id.foto_prodotto)
        val add: ImageView = findViewById(R.id.imageViewAdd)
        val remove: ImageView = findViewById(R.id.imageViewRemove)
        val quantita: TextView = findViewById(R.id.textViewQuantita)
        val addList: Button = findViewById(R.id.buttonAddList)

        val nomeProdotto = intent.getStringExtra("nome_prodotto")
        val prezzoProdotto = intent.getFloatExtra("prezzo_prodotto", 0f)
        val descrizioneProdotto = intent.getStringExtra("descrizione_prodotto")
        val fotoProdotto = intent.getStringExtra("foto_prodotto")
        val quantitaProdotto = intent.getFloatExtra("quantita_prodotto", 0.5f)

        if (nomeProdotto != null) {
            dettaglioProdottiViewModel.setNome(nomeProdotto)
        }
        dettaglioProdottiViewModel.nome_prodotto.observe(this) {
            nome.text = it
        }

        dettaglioProdottiViewModel.setPrezzo(prezzoProdotto)
        dettaglioProdottiViewModel.prezzo_prodotto.observe(this) {
            prezzo.text = "€$it"
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

        add.setOnClickListener{
            dettaglioProdottiViewModel.incrementaQuantita()
        }
        remove.setOnClickListener{
            dettaglioProdottiViewModel.decrementaQuantita()
        }

        dettaglioProdottiViewModel.setQuantita(quantitaProdotto)
        dettaglioProdottiViewModel.quantita_prodotto.observe(this) {
            quantita.text = it.toString()
        }

        addList.setOnClickListener{
            dettaglioProdottiViewModel.inserimentoProdottoInListaSpesa()
        }

    }
}