package com.example.greenmarket.ui.altro.storico

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R
import com.example.greenmarket.ui.altro.storico.dettaglio_scontrini.DettaglioScontriniActivity

class StoricoActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        val storicoViewModel: StoricoViewModel by viewModels()

        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_storico)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val adapter = StoricoListAdapter() {
            currentScontrino ->
            run {
                //Toast.makeText(this, currentScontrino.data, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DettaglioScontriniActivity::class.java)
                intent.putExtra("data_scontrino", currentScontrino.data)
                intent.putExtra("codice_sconto", currentScontrino.codice_sconto)
                startActivity(intent)
            }
        }
        val recyclerView = findViewById<RecyclerView>(R.id.rv_scontrini)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        /*val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val dividerDrawable = ColorDrawable(ContextCompat.getColor(this, R.color.black))
        dividerItemDecoration.setDrawable(dividerDrawable)
        recyclerView.addItemDecoration(dividerItemDecoration)*/

        storicoViewModel.readScontrini()
        storicoViewModel.listaScontrini.observe(this, Observer {
            scontrino -> adapter.setData(scontrino)
        })

    }
}