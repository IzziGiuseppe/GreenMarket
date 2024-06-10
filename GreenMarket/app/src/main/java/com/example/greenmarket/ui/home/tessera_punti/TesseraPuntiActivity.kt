package com.example.greenmarket.ui.home.tessera_punti

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R

class TesseraPuntiActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        val tesseraPuntiViewModel: TesseraPuntiViewModel by viewModels()

        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_tessera_punti)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val adapter = CodiciScontoListAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.rv_codici_sconto)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        /*val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val dividerDrawable = ColorDrawable(ContextCompat.getColor(this, R.color.black))
        dividerItemDecoration.setDrawable(dividerDrawable)
        recyclerView.addItemDecoration(dividerItemDecoration)*/

        tesseraPuntiViewModel.readCodiciSconto()
        tesseraPuntiViewModel.listaCodiciSconto.observe(this, Observer {
                cs -> adapter.setData(cs)
        })
    }
}