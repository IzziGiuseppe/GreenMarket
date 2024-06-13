package com.example.greenmarket.ui.lista_spesa.conferma_ordine

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.greenmarket.R
import com.example.greenmarket.ui.home.tessera_punti.TesseraPuntiViewModel

class ConfermaOrdineActivity : AppCompatActivity() {
    //private var _binding: ActivityConfermaOrdineBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = _binding!!

    private val confermaOrdineViewModel: ConfermaOrdineViewModel by viewModels()
    private val tesseraPuntiViewModel: TesseraPuntiViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conferma_ordine)

        val confermaBT: Button = findViewById(R.id.conferma_ordine_totale)
        val prezzoTotale: TextView = findViewById(R.id.prezzo_totale_no_sconto)

        val editTextCS: Spinner = findViewById(R.id.edit_codice_sconto)
        confermaOrdineViewModel.readCodiciSconto()
        confermaOrdineViewModel.listaCodiciSconto.observe(this) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, it)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            editTextCS.adapter = adapter
        }

        editTextCS.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position != 0) { // Ignora il primo elemento "Select a Language"
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    Toast.makeText(this@ConfermaOrdineActivity, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

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
            //confermaOrdineViewModel.deleteListaSpesa()

            Toast.makeText(this, "Acquisto effettuato con successo", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}