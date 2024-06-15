package com.example.greenmarket.ui.lista_spesa.conferma_ordine

import android.content.Intent
import android.os.Build
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.greenmarket.MainActivity
import com.example.greenmarket.R
import com.example.greenmarket.ui.home.tessera_punti.TesseraPuntiViewModel
import com.example.greenmarket.ui.lista_spesa.ListaSpesaFragment
import com.example.greenmarket.ui.lista_spesa.ListaSpesaViewModel
import java.math.BigDecimal
import java.math.RoundingMode
import com.example.greenmarket.databinding.ActivityConfermaOrdineBinding
import com.example.greenmarket.databinding.FragmentListaSpesaBinding
import com.example.greenmarket.ui.ricerca.RicercaFragment
import com.example.greenmarket.ui.ricerca.dettaglio_prodotti.DettaglioProdottiViewModel

class ConfermaOrdineActivity : AppCompatActivity() {
    //private var _binding: ActivityConfermaOrdineBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = _binding!!

    private val confermaOrdineViewModel: ConfermaOrdineViewModel by viewModels()
    private val listaSpesaViewModel: ListaSpesaViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conferma_ordine)

        val prezzoTotaleLista = intent.getStringExtra("prezzo_totale")

        val confermaBT: Button = findViewById(R.id.conferma_ordine_totale)
        val prezzoTotale: TextView = findViewById(R.id.prezzo_totale_no_sconto)
        val valoreSconto: TextView = findViewById(R.id.valore_sconto)
        val prezzoScontato: TextView = findViewById(R.id.prezzo_totale_si_sconto)
        val editIndirizzo: EditText = findViewById(R.id.edit_indirizzo)

        var codiceScontoUtilizzato: String

        val editTextCS: Spinner = findViewById(R.id.edit_codice_sconto)
        confermaOrdineViewModel.readCodiciSconto()
        confermaOrdineViewModel.listaCodiciSconto.observe(this) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, it)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            editTextCS.adapter = adapter
        }

        editTextCS.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position != 0) { // Ignora il primo elemento
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    confermaOrdineViewModel.setCodiceSconto(selectedItem)
                    //Toast.makeText(this@ConfermaOrdineActivity, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
                    if (prezzoTotaleLista != null) {
                        val valSconto = BigDecimal(((prezzoTotaleLista.toFloat() * 5)/100).toString()).setScale(2, RoundingMode.HALF_DOWN)
                        val prezzoScontatoFinale = BigDecimal((prezzoTotaleLista.toFloat() - valSconto.toFloat()).toString()).setScale(2, RoundingMode.HALF_DOWN)
                        confermaOrdineViewModel.setValoreSconto("€$valSconto")
                        confermaOrdineViewModel.setPrezzoScontato(prezzoScontatoFinale.toString())
                    }
                }
                else {
                    confermaOrdineViewModel.setValoreSconto("-")
                    confermaOrdineViewModel.prezzo_totale.value?.let {
                        confermaOrdineViewModel.setPrezzoScontato(
                            it
                        )
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        if (prezzoTotaleLista != null) {
            confermaOrdineViewModel.setPrezzoTotale(prezzoTotaleLista)
        }
        confermaOrdineViewModel.prezzo_totale.observe(this) {
            prezzoTotale.text = "€$it"
        }

        confermaOrdineViewModel.valore_sconto.observe(this) {
            valoreSconto.text = it
        }

        confermaOrdineViewModel.prezzo_scontato.observe(this) {
            prezzoScontato.text = "€$it"
        }

        confermaOrdineViewModel.readVia()
        confermaOrdineViewModel.via.observe(this) {
            editIndirizzo.setText(it)
        }

        confermaBT.setOnClickListener {
            confermaOrdineViewModel.aggiornaSaldo()
            confermaOrdineViewModel.codice_sconto.observe(this) {
                codiceScontoUtilizzato = it
                if (codiceScontoUtilizzato != "-") {
                    confermaOrdineViewModel.deleteCodiceSconto(codiceScontoUtilizzato)
                }
            }
            //Implementare la funzione che gestisce la creazione di una scontrino
            //confermaOrdineViewModel.deleteListaSpesa()
            listaSpesaViewModel.deleteListaSpesa()

            confermaOrdineViewModel.creaScontrino()
            Toast.makeText(this, "Acquisto effettuato con successo", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}