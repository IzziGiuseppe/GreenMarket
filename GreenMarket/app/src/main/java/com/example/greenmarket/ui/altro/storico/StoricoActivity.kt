package com.example.greenmarket.ui.altro.storico

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.greenmarket.R

class StoricoActivity : AppCompatActivity() {
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

        val text = findViewById<TextView>(R.id.storico_txt)

        storicoViewModel.text.observe(this) {
            text.text = it
        }

    }
}