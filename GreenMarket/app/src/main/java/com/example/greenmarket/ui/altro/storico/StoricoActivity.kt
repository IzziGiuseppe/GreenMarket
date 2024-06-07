package com.example.greenmarket.ui.altro.storico

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R

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
                Toast.makeText(this, currentScontrino.data, Toast.LENGTH_SHORT).show()
            }
        }
        val recyclerView = findViewById<RecyclerView>(R.id.rv_scontrini)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        storicoViewModel.readScontrini()
        storicoViewModel.listaScontrini.observe(this, Observer {
            scontrino -> adapter.setData(scontrino)
        })

    }
}