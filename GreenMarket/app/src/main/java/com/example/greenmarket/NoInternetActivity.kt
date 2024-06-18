package com.example.greenmarket

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginBottom
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NoInternetActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_no_internet)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val button = findViewById<Button>(R.id.buttonUP)
        val dino = findViewById<ImageView>(R.id.dino)

        button.setOnClickListener {
            // Avvia una coroutine nel contesto di Main (UI) thread
            lifecycleScope.launch(Dispatchers.Main) {
                // Setta il margine a 8dp
                setMargin(dino, 128)

                // Attendi 1 secondo
                delay(500)

                // Setta il margine a 0
                setMargin(dino, 0)
            }

        }

    }
    fun setMargin(view: View, marginDp: Int) {
        val params = view.layoutParams as ViewGroup.MarginLayoutParams
        val scale = resources.displayMetrics.density
        val marginPx = (marginDp * scale + 0.5f).toInt()
        params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, marginPx)
        view.layoutParams = params
    }
}