package com.example.greenmarket.ui.altro.donazioni

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.greenmarket.R

class DonazioniActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donazioni)

        val button = findViewById<Button>(R.id.buttonDona)
        val edit = findViewById<EditText>(R.id.editTextDonazioni)

        button.setOnClickListener {
            if (!edit.text.toString().matches(Regex("^\\d+(\\.\\d{1,2})?\$"))) {
                Toast.makeText(this, "Inserire una cifra valida!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Grazie per averci supportato! \uD83D\uDE0A", Toast.LENGTH_SHORT).show()
                Toast.makeText(this,
                    "Ovviamente è una donazione fittizia.",
                    Toast.LENGTH_LONG).show()
                Toast.makeText(this,
                    "Ma grazie lo stesso per il pensiero :D",
                    Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}