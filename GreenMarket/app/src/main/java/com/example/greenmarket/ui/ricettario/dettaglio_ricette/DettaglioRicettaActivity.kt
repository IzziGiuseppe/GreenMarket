package com.example.greenmarket.ui.ricettario.dettaglio_ricette

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
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
        val button: Button = findViewById(R.id.buttonSearchRicetta)

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

        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEARCH).apply {
                setPackage("com.google.android.youtube")
                putExtra("query", "Ricetta $nomeRicetta")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }

            // Verifica se esiste un'app in grado di gestire questo intent
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                // Se non c'è l'app di YouTube, apri la ricerca nel browser
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=ricetta+$nomeRicetta"))
                startActivity(webIntent)
            }
        }
    }
}