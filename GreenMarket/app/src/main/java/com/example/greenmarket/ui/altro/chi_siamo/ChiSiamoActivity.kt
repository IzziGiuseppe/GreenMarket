package com.example.greenmarket.ui.altro.chi_siamo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.greenmarket.R

class ChiSiamoActivity : AppCompatActivity() {

    private val chiSiamoViewModel: ChiSiamoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_chi_siamo)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val titolo: TextView = findViewById(R.id.titolo_txt)
        val testo: TextView = findViewById(R.id.testo_txt)
        val titolo2: TextView = findViewById(R.id.titolo2_txt)
        val giusBt: Button = findViewById(R.id.giuseppe_bt)
        val domBt: Button = findViewById(R.id.domenico_bt)

        chiSiamoViewModel.storia_azienda.observe(this) {
            titolo.text = it
        }

        chiSiamoViewModel.text.observe(this) {
            testo.text = it
        }

        chiSiamoViewModel.dove_siamo.observe(this) {
            titolo2.text = it
        }

        chiSiamoViewModel.giuse.observe(this) {
            giusBt.text = it
        }

        chiSiamoViewModel.dom.observe(this) {
            domBt.text = it
        }

        giusBt.setOnClickListener {
            chiSiamoViewModel.giuse_action()
        }

        domBt.setOnClickListener {
            chiSiamoViewModel.dom_action()
        }

    }

}