package com.example.greenmarket.ui.altro.chi_siamo

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.greenmarket.R
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
        //enableEdgeToEdge()
        setContentView(R.layout.activity_chi_siamo)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        mapView = findViewById(R.id.mappa)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        val titolo: TextView = findViewById(R.id.titolo_txt)
        val testo: TextView = findViewById(R.id.testo_txt)
        val titolo2: TextView = findViewById(R.id.titolo2_txt)
        val giusBtLinkedin: Button = findViewById(R.id.linkedin_giuseppe)
        val domBtLinkedin: Button = findViewById(R.id.linkedin_domenico)
        val giusBtGitHub: Button = findViewById(R.id.github_giuseppe)
        val domBtGitHub: Button = findViewById(R.id.github_domenico)
        val giusBtOutlook: Button = findViewById(R.id.outlook_giuseppe)
        val domBtOutlook: Button = findViewById(R.id.outlook_domenico)

        chiSiamoViewModel.storia_azienda.observe(this) {
            titolo.text = it
        }

        chiSiamoViewModel.text.observe(this) {
            testo.text = it
        }

        chiSiamoViewModel.dove_siamo.observe(this) {
            titolo2.text = it
        }

        giusBtLinkedin.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://www.linkedin.com/feed/"))
            startActivity(intent)
        }

        domBtLinkedin.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://www.linkedin.com/feed/"))
            startActivity(intent)
        }

        giusBtGitHub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://www.univpm.com/"))
            startActivity(intent)
        }

        domBtGitHub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://github.com/domenicolaporta00"))
            startActivity(intent)
        }

        giusBtOutlook.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:s1094052@studenti.univpm.it")
            }

            try {
                this.startActivity(Intent.createChooser(emailIntent, "Scegli un'app per email"))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Non ci sono app di email installate.", Toast.LENGTH_SHORT).show()
            }
        }

        domBtOutlook.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:s1095492@studenti.univpm.it")
            }

            try {
                this.startActivity(Intent.createChooser(emailIntent, "Scegli un'app per email"))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Non ci sono app di email installate.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
        val location = LatLng(43.5855532, 13.5151101)
        googleMap.addMarker(MarkerOptions().position(location).title("UNIVPM"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
    }

}