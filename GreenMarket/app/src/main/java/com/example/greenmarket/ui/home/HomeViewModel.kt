package com.example.greenmarket.ui.home

import android.app.Application
import android.os.Build
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.greenmarket.db.GMDatabase
import com.example.greenmarket.db.model.Prodotto
import com.example.greenmarket.ui.ricerca.ProdottoModel
import com.example.greenmarket.ui.ricettario.RicettaModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.LocalDateTime
import kotlin.random.Random

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "Home"
    }
    val text: LiveData<String> = _text

    private val _orari = MutableLiveData<String>().apply {
        value = ""
    }
    val orari: LiveData<String> = _orari

    private val db:GMDatabase = GMDatabase.getInstance(application)

    private var dbf: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var _prodotto = MutableLiveData(Prodotto("", "", 0f, "", ""))
    val prodotto: LiveData<Prodotto>
        get() = _prodotto

    private var _listaProdotti = MutableLiveData(listOf<ProdottoModel>())
    val listaProdotti: MutableLiveData<List<ProdottoModel>>
        get() = _listaProdotti

    private var _listaRicette = MutableLiveData<List<RicettaModel>>()
    val listaRicette: MutableLiveData<List<RicettaModel>> = _listaRicette

    private val _status = MutableLiveData<String>().apply {
        value = ""
    }
    var status: LiveData<String> = _status

    fun insert(vararg prodotto: Prodotto){
        db.ProdottoDao().insert(*prodotto)
    }

    fun readRicetteRandom() {
        dbf.collection("recipes").get()
            .addOnSuccessListener { documents ->
                _listaRicette.value = documents.toObjects(RicettaModel::class.java).randomElements(2)
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Error getting products", exception)
            }
    }

    fun readProdottiRandom() {
        dbf.collection("products").get()
            .addOnSuccessListener { documents ->
                _listaProdotti.value = documents.toObjects(ProdottoModel::class.java).randomElements(3)
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Error getting products", exception)
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun readNome(fragment: HomeFragment, imageProfilo: ImageView) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        // Ottenere l'ID dell'utente corrente
        if (currentUser != null) {
            val userID = currentUser.uid
            Log.d("UserProfileActivity", "User ID: $userID")  // Log l'ID dell'utente corrente

            dbf.collection("users").document(userID).get()
                .addOnSuccessListener {
                    if (it != null && it.exists()) {
                        val name = it.getString("nome")
                        _text.value = "Ciao $name \uD83D\uDC4B"
                        val photo = it.getString("foto")

                        if (!photo.isNullOrEmpty()) {
                            Glide.with(fragment)
                                .load(photo)
                                .into(imageProfilo)
                        } else {
                            Log.d("UserProfileActivity", "Nessuna immagine trovata per l'utente.")
                        }
                    } else {
                        // Documento non trovato
                        Log.w("UserProfileActivity", "Documento non trovato")
                        Toast.makeText(getApplication(), "Dati non visualizzabili", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    // Gestisci l'errore
                    Log.e("UserProfileActivity", "Errore nel caricamento dei dati")
                    Toast.makeText(getApplication(), "Errore nel caricamento dei dati", Toast.LENGTH_SHORT).show()
                }
        } else {
            // Utente non loggato
            Log.w("UserProfileActivity", "Utente non loggato")
            Toast.makeText(getApplication(), "Utente non loggato", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateStatus() {
        val currentTime = LocalDateTime.now().toLocalTime()

        val open = LocalTime.of(8, 30)
        val close = LocalTime.of(20, 30)

        _status.value = if (currentTime in open..close) {
            "Aperto"
        } else {
            "Chiuso"
        }

        _orari.value = if (currentTime in open..close) {
            "Chiude alle $close"
        } else {
            "Apre alle $open"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateStatusRegularly(scope: CoroutineScope) {
        scope.launch {
            while (true) {
                updateStatus()
                delay(30000) // aggiorna ogni trenta secondi
            }
        }
    }

    fun <T> List<T>.randomElements(n: Int): List<T> {
        return this.shuffled().take(n)
    }


}