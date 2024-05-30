package com.example.greenmarket.ui.altro.termini_condizioni

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R

class TermCondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        val termCondViewModel: TermCondViewModel by viewModels()

        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_term_cond)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val adapter = TermCondListAdapter()
        val rv = findViewById<RecyclerView>(R.id.rv_tc)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        termCondViewModel.text.observe(this, Observer {
            txt -> adapter.setData(txt)
        })

        /*val testo = findViewById<TextView>(R.id.testo_tc_txt)
        termCondViewModel.text.observe(this) {
            testo.text = it
        }*/

    }
}