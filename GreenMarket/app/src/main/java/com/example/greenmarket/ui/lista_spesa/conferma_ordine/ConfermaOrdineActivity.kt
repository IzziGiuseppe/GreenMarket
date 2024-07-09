package com.example.greenmarket.ui.lista_spesa.conferma_ordine

import android.os.Build
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.greenmarket.ui.internet.InternetTest
import com.example.greenmarket.R
import com.example.greenmarket.ui.lista_spesa.ListaSpesaViewModel
import java.math.BigDecimal
import java.math.RoundingMode

class ConfermaOrdineActivity : AppCompatActivity() {

    private val confermaOrdineViewModel: ConfermaOrdineViewModel by viewModels()
    private val listaSpesaViewModel: ListaSpesaViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conferma_ordine)

        val iT = InternetTest()

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

        //funzione per la gestione del codice sconto selezionato
        editTextCS.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position != 0) { // Ignora il primo elemento
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    //prende il codice sconto selezionato dallo Spinner
                    confermaOrdineViewModel.setCodiceSconto(selectedItem)
                    if (prezzoTotaleLista != null) {
                        val valSconto = BigDecimal(((prezzoTotaleLista.toFloat() * 5)/100).toString()).setScale(2, RoundingMode.HALF_DOWN)
                        val prezzoScontatoFinale = BigDecimal((prezzoTotaleLista.toFloat() - valSconto.toFloat()).toString()).setScale(2, RoundingMode.HALF_DOWN)
                        //Imposta valore sconto e prezzo scontato
                        confermaOrdineViewModel.setValoreSconto("€$valSconto")
                        confermaOrdineViewModel.setPrezzoScontato(prezzoScontatoFinale.toString())
                    }
                }
                else {
                    //gestione quando non viene selezionato nessun codice sconto
                    //nonostante ce ne siano a disposizione
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
            if(iT.isInternetAvailable(this)){
                //Implementa la funzione che gestisce la creazione di una scontrino,
                //l'aggiornamento delle statistiche e del saldo della tessera punti
                //e lo svuotamento della lista della spesa
                confermaOrdineViewModel.creaScontrino(this)
                finish()
            }else{
                iT.toast(this)
            }
        }
    }
}