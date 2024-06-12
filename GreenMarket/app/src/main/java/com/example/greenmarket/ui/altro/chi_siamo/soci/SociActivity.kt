package com.example.greenmarket.ui.altro.chi_siamo.soci

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.greenmarket.R

class SociActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soci)

        val linkedin = intent.getStringExtra("linkedin")
        val github = intent.getStringExtra("github")
        val outlook = intent.getStringExtra("outlook")
        val socio = intent.getStringExtra("socio")
        val foto = intent.getStringExtra("foto")

        val linkedinBt = findViewById<ImageView>(R.id.image_linkedin)
        val githubBt = findViewById<ImageView>(R.id.image_git)
        val outlookBt = findViewById<ImageView>(R.id.image_outlook)
        val socioTxt = findViewById<TextView>(R.id.nome_socio)
        val fotoSocio = findViewById<ImageView>(R.id.imageViewSocio)

        socioTxt.text = socio
        fotoSocio.setImageResource(resources.getIdentifier(foto, "drawable", packageName))

        linkedinBt.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedin))
            startActivity(intent)
        }

        githubBt.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(github))
            startActivity(intent)
        }

        outlookBt.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse(outlook)
            }

            try {
                this.startActivity(Intent.createChooser(emailIntent, "Scegli un'app per email"))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Non ci sono app di email installate.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}