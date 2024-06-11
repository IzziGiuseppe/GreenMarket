package com.example.greenmarket.ui.ricettario.dettaglio_ricette

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.greenmarket.R

class DettaglioRicettaActivity : AppCompatActivity() {

    private val dettaglioRicetteViewModel: DettaglioRicetteViewModel by viewModels()

    @SuppressLint("MissingInflatedId", "DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dettaglio_ricetta)

        val nome: TextView = findViewById(R.id.nome_ricetta)
        val descrizione: TextView = findViewById(R.id.descrizione_ricetta)
        val foto: ImageView = findViewById(R.id.foto_ricetta)

        val nomeRicetta = intent.getStringExtra("nome_ricetta")
        val descrizioneRicetta = intent.getStringExtra("descrizione_ricetta")
        val fotoRicetta = intent.getStringExtra("foto_ricetta")
        val ingredientiRicetta = intent.getStringExtra("ingredienti_ricetta")

        if (nomeRicetta != null) {
            dettaglioRicetteViewModel.setNome(nomeRicetta)
        }
        dettaglioRicetteViewModel.nome_ricetta.observe(this) {
            nome.text = it
        }

        if (descrizioneRicetta != null) {
            dettaglioRicetteViewModel.setDescrizione(descrizioneRicetta)
        }
        dettaglioRicetteViewModel.descrizione_ricetta.observe(this) {
            descrizione.text = it
        }

        if (!fotoRicetta.isNullOrEmpty()) {
            dettaglioRicetteViewModel.setFoto(fotoRicetta)
        }
        dettaglioRicetteViewModel.foto_ricetta.observe(this) {
            Glide.with(this)
                .load(fotoRicetta)
                .into(foto)
        }

        if (ingredientiRicetta != null) {
            dettaglioRicetteViewModel.ingredienti_ricetta.observe(this) {
                /*
                ingredienti.text = ingredientiRicetta
                 */
            }
        }
        dettaglioRicetteViewModel.nome_ricetta.observe(this) {
            nome.text = it
        }
    }
}