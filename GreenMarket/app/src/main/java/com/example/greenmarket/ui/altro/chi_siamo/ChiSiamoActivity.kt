package com.example.greenmarket.ui.altro.chi_siamo

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.greenmarket.R
import com.example.greenmarket.ui.altro.chi_siamo.soci.SociActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ChiSiamoActivity : AppCompatActivity(), OnMapReadyCallback {

    private val chiSiamoViewModel: ChiSiamoViewModel by viewModels()

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_siamo)

        mapView = findViewById(R.id.mappa)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        val titolo: TextView = findViewById(R.id.titolo_txt)
        val testo: TextView = findViewById(R.id.testo_txt)
        val titolo2: TextView = findViewById(R.id.titolo2_txt)
        val giuseppe: ImageView = findViewById(R.id.imageViewGiuseppe)
        val domenico: ImageView = findViewById(R.id.imageViewDomenico)

        chiSiamoViewModel.storia_azienda.observe(this) {
            titolo.text = it
        }

        chiSiamoViewModel.text.observe(this) {
            testo.text = it
        }

        chiSiamoViewModel.dove_siamo.observe(this) {
            titolo2.text = it
        }

        giuseppe.setOnClickListener {
            startSoci("https://www.linkedin.com/feed/",
                "https://github.com/IzziGiuseppe",
                "mailto:s1094052@studenti.univpm.it",
                "Giuseppe Izzi",
                "@drawable/giuseppe")
        }

        domenico.setOnClickListener {
            startSoci("https://www.linkedin.com/feed/",
                "https://github.com/domenicolaporta00",
                "mailto:s1095492@studenti.univpm.it",
                "Domenico La Porta",
                "@drawable/domenico")
        }

    }

    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
        val location = LatLng(43.5855532, 13.5151101)
        googleMap.addMarker(MarkerOptions().position(location).title("UNIVPM"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
    }

    fun startSoci(linkedin: String, github: String, outlook: String, socio: String, foto: String) {
        val intent = Intent(this, SociActivity::class.java)
        intent.putExtra("linkedin", linkedin)
        intent.putExtra("github", github)
        intent.putExtra("outlook", outlook)
        intent.putExtra("socio", socio)
        intent.putExtra("foto", foto)
        startActivity(intent)
    }

}