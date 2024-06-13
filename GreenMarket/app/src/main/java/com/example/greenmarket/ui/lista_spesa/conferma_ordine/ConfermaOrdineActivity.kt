package com.example.greenmarket.ui.lista_spesa.conferma_ordine

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.greenmarket.R
import com.example.greenmarket.databinding.ActivityConfermaOrdineBinding
import com.example.greenmarket.databinding.FragmentListaSpesaBinding
import com.example.greenmarket.ui.ricerca.dettaglio_prodotti.DettaglioProdottiViewModel

class ConfermaOrdineActivity : AppCompatActivity() {
    //private var _binding: ActivityConfermaOrdineBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = _binding!!

    private val confermaOrdineViewModel: ConfermaOrdineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conferma_ordine)

        val confermaBT: Button = findViewById(R.id.conferma_ordine_totale)
        val prezzoTotale: TextView = findViewById(R.id.prezzo_totale_no_sconto)

        val prezzoTotaleLista = intent.getStringExtra("prezzo_totale")

        if (prezzoTotaleLista != null) {
            confermaOrdineViewModel.setPrezzoTotale(prezzoTotaleLista)
        }
        confermaOrdineViewModel.prezzo_totale.observe(this) {
            prezzoTotale.text = "€$it"
        }

        confermaBT.setOnClickListener {
            confermaOrdineViewModel.aggiornaSaldo()
            //Implementare la funzione che gestisce la creazione di una scontrino
            confermaOrdineViewModel.deleteListaSpesa()

            Toast.makeText(this, "Acquisto effettuato con successo", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}