package com.example.greenmarket.ui.home.tessera_punti

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R
import com.example.greenmarket.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class TesseraPuntiActivity : AppCompatActivity() {

    val tesseraPuntiViewModel: TesseraPuntiViewModel by viewModels()
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tessera_punti)

        val saldo: TextView = findViewById(R.id.valore_spesa)
        val punti: TextView = findViewById(R.id.valore_punti)
        val converti: Button = findViewById(R.id.converti)
        val riscatta: Button = findViewById(R.id.riscatta)

        val adapter = CodiciScontoListAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.rv_codici_sconto)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        tesseraPuntiViewModel.readCodiciSconto()
        tesseraPuntiViewModel.listaCodiciSconto.observe(this) { cs ->
            adapter.setData(cs)
        }

        tesseraPuntiViewModel.readTesseraPunti()
        tesseraPuntiViewModel.saldo.observe(this) {
            saldo.text = "€$it"
        }
        tesseraPuntiViewModel.punti.observe(this) {
            punti.text = it.toString()
        }

        converti.setOnClickListener{
            tesseraPuntiViewModel.convertiSaldo()
        }

        riscatta.setOnClickListener{
            tesseraPuntiViewModel.riscattaCodiceSconto()
        }



        val infoCS = findViewById<ImageButton>(R.id.infoCS)
        infoCS.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Info codici sconto")
            builder.setMessage("● Ogni €10 di spesa ottieni, cliccando sul pulsante CONVERTI IN PUNTI, 1 punto.\n" +
                    "● I punti sono cumulabili e l'eventuale resto dei soldi spesi viene mantenuto.\n" +
                    "● Ogni 5 punti ottieni, cliccando sul pulsante OTTIENI CODICI SCONTO, un codice sconto.\n" +
                    "● L'eventuale resto dei punti viene mantenuto.\n" +
                    "● I codici sconto possono essere utilizzati esclusivamente dall'utente proprietario del profilo e non sono scambiabili.\n" +
                    "● Ogni codice corrisponde ad uno sconto del 5% sul costo totale dello scontrino a cui viene applicato.\n" +
                    "● Si potrà utilizzare esclusivamente un codice sconto per ogni scontrino.\n" +
                    "● Ogni codice sconto potrà essere utilizzato una sola volta; dopo il suo utilizzo verrà eliminato dai codici sconto disponibili."
            )

            builder.setPositiveButton("Ho capito!") { dialog, _ ->
                dialog.dismiss()
            }

            builder.setNegativeButton("Qualcosa non mi è chiaro.") { _, _ ->
                this.assistenza()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    private fun assistenza() {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:domenico.laporta.dlp@gmail.com")
        }

        try {
            this.startActivity(Intent.createChooser(emailIntent, "Scegli un'app per email"))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Non ci sono app di email installate.", Toast.LENGTH_SHORT).show()
        }
    }
}