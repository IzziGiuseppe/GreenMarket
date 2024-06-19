package com.example.greenmarket.ui.lista_spesa.conferma_ordine

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.greenmarket.ui.home.tessera_punti.TesseraPuntiModel
import com.example.greenmarket.ui.lista_spesa.ListaDellaSpesaModel
import com.example.greenmarket.ui.lista_spesa.ProdottoInListaModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConfermaOrdineViewModel(application: Application): AndroidViewModel(application){
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser

    private val _tessera_punti = MutableLiveData<TesseraPuntiModel>()
    val tessera_punti: LiveData<TesseraPuntiModel> = _tessera_punti

    private var _prezzo_totale = MutableLiveData<String>().apply {
        value = ""
    }
    val prezzo_totale: LiveData<String> = _prezzo_totale

    private var _listaSpesa = MutableLiveData<ListaDellaSpesaModel>()
    val listaSpesa: MutableLiveData<ListaDellaSpesaModel>
        get() = _listaSpesa

    private var _lista_prodotti = MutableLiveData<List<ProdottoInListaModel>>()
    val lista_prodotti: MutableLiveData<List<ProdottoInListaModel>>
        get() = _lista_prodotti

    private var _statistiche = MutableLiveData<Map<String, Float>>()
    val statistiche: LiveData<Map<String, Float>>
        get() = _statistiche

    private var _scotrnino = MutableLiveData<String>().apply {
        value = "Scontrino"
    }
    val scontrino: LiveData<String> = _scotrnino

    private var _valore_sconto = MutableLiveData<String>().apply {
        value = ""
    }
    val valore_sconto: LiveData<String> = _valore_sconto

    private var _prezzo_scontato = MutableLiveData<String>().apply {
        value = ""
    }
    val prezzo_scontato: LiveData<String> = _prezzo_scontato

    private var _listaCodiciSconto= MutableLiveData(listOf<String>())
    val listaCodiciSconto: MutableLiveData<List<String>>
        get() = _listaCodiciSconto

    private var _codice_sconto = MutableLiveData<String>().apply {
        value = "-"
    }
    val codice_sconto: LiveData<String> = _codice_sconto

    private var _via = MutableLiveData<String>().apply {
        value = ""
    }
    val via: LiveData<String> = _via

    @OptIn(UnstableApi::class)
    fun setPrezzoTotale(prezzoTotale: String) {
        _prezzo_totale.value = prezzoTotale
    }

    fun setValoreSconto(valoreSconto: String) {
        _valore_sconto.value = valoreSconto
    }

    fun setPrezzoScontato(prezzoScontato: String) {
        _prezzo_scontato.value = prezzoScontato
    }

    fun setCodiceSconto(cs: String) {
        _codice_sconto.value = cs
    }

    @OptIn(UnstableApi::class)
    fun aggiornaSaldo() {
        currentUser?.let {
            db.collection("users").document(it.uid).collection("pointCard").document("wallet").get()
                .addOnSuccessListener {document ->
                    _tessera_punti.value = document.toObject(TesseraPuntiModel::class.java)
                    val saldo = _prezzo_scontato.value?.toFloat()?.plus(_tessera_punti.value?.saldo!!)
                    val saldoArrotondato = BigDecimal(saldo.toString()).setScale(2, RoundingMode.HALF_DOWN)

                    val updates = hashMapOf(
                        "punti" to _tessera_punti.value?.punti,
                        "saldo" to saldoArrotondato.toFloat()
                    )
                    db.collection("users").document(it.uid).collection("pointCard").document("wallet")
                        .set(updates)
                }
        }

    }


    @OptIn(UnstableApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    fun creaScontrino() {
        currentUser.let {
            db.collection("users").document(currentUser?.uid!!).collection("historical")
                .document("shoppingList").get()
                .addOnSuccessListener { documents ->
                    _listaSpesa.value = documents.toObject(ListaDellaSpesaModel::class.java)
                    _lista_prodotti.value = _listaSpesa.value?.let { it1 -> listaProdotti(it1.prodotti) }
                    val dataScontrino = getCurrentDateTime()
                    val prezzo_scontato: Float
                    val sconto = (_prezzo_totale.value?.toFloat()?.times(5))?.div(100)
                    val scontoArrotondato = BigDecimal(sconto.toString()).setScale(2, RoundingMode.HALF_EVEN).toFloat()
                    if(_codice_sconto.value != "-"){
                        prezzo_scontato = _prezzo_totale.value?.toFloat()?.minus(scontoArrotondato)!!
                    }else{
                        prezzo_scontato = BigDecimal((_prezzo_totale.value?.toFloat()!!).toString())
                            .setScale(2, RoundingMode.HALF_EVEN).toFloat()
                    }
                    val nuovoScontrino = hashMapOf(
                        "data" to dataScontrino,
                        "valido" to true,
                        "prodotti" to _listaSpesa.value?.prodotti,
                        "totale" to BigDecimal(prezzo_scontato.toString())
                            .setScale(2, RoundingMode.HALF_EVEN).toFloat(),
                        "codiceSconto" to _codice_sconto.value,
                        "valoreSconto" to _valore_sconto.value
                    )

                    if (it != null) {
                        db.collection("users").document(it.uid).collection("historical")
                            .document(dataScontrino).set(nuovoScontrino)
                            .addOnSuccessListener {
                                //Gestiamo le statistiche
                                db.collection("users").document(currentUser.uid).collection("stats").document("top_selling_products").get()
                                    .addOnSuccessListener { document->
                                        val prodottiMap = document.data
                                        if (prodottiMap != null) {
                                            for(prodotto in _lista_prodotti.value!!){
                                                for((key, value) in prodottiMap){
                                                    if (key == prodotto.nome && value is Number) {
                                                        var floatValue = value.toFloat()
                                                        floatValue += prodotto.quantita
                                                        db.collection("users").document(currentUser.uid).collection("stats").document("top_selling_products").update(prodotto.nome, floatValue)
                                                    }
                                                }
                                            }
                                        }
                                    }
                            }
                            .addOnFailureListener{
                                Toast.makeText(getApplication(), "Errore durante lo svuotamente della lista della spesa", Toast.LENGTH_SHORT).show()
                            }
                    }
                }


        }
    }

    fun readVia() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        // Ottenere l'ID dell'utente corrente
        if (currentUser != null) {
            val userID = currentUser.uid
            android.util.Log.d("UserProfileActivity", "User ID: $userID")  // Log l'ID dell'utente corrente

            db.collection("users").document(userID).get()
                .addOnSuccessListener {
                    if (it != null && it.exists()) {
                        val indirizzo = it.getString("indirizzo")
                        _via.value = "$indirizzo"
                    } else {
                        // Documento non trovato
                        android.util.Log.w("UserProfileActivity", "Documento non trovato")
                        Toast.makeText(getApplication(), "Dati non visualizzabili", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    // Gestisci l'errore
                    android.util.Log.e("UserProfileActivity", "Errore nel caricamento dei dati")
                    Toast.makeText(getApplication(), "Errore nel caricamento dei dati", Toast.LENGTH_SHORT).show()
                }
        } else {
            // Utente non loggato
            android.util.Log.w("UserProfileActivity", "Utente non loggato")
            Toast.makeText(getApplication(), "Utente non loggato", Toast.LENGTH_SHORT).show()
        }
    }

    fun readCodiciSconto() {
        currentUser?.let {
            db.collection("users").document(it.uid).collection("pointCard").document("coupons")
                .get()
                .addOnSuccessListener { document ->
                    val vect = document.get("codici sconto") as? List<String>
                    if (vect != null) {
                        if (vect.isEmpty()) {
                            _listaCodiciSconto.value = listOf("Non ci sono codici sconto a disposizione")
                        }
                        else {
                            val mutableVect = vect.toMutableList()
                            mutableVect.add(0, "-")
                            _listaCodiciSconto.value = mutableVect
                        }

                    }
                }
        }
    }

    @OptIn(UnstableApi::class)
    //funzione che elimina il codice sconto utilizzato dalla lista dei codici sconto disponibili
    fun deleteCodiceSconto(cs: String) {
        if (_listaCodiciSconto.value?.contains(cs) == true) {
            currentUser.let {
                it?.let { it1 ->
                    db.collection("users").document(it1.uid).collection("pointCard").document("coupons").update("codici sconto", FieldValue.arrayRemove(cs))
                        .addOnSuccessListener {
                            Log.d("Buono", "Elemento rimosso con successo dalla lista")
                        }
                        .addOnFailureListener { e ->
                            Log.w("Errore", "Errore nella rimozione dell'elemento", e)
                        }
                }
            }
        }
        else {
            Log.d("CODICE SCONTO ELIMINATO", "C'è stato un problema")
        }
    }

    //Funzione che calcola la data e l'ora attuale
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDateTime(): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return currentDateTime.format(formatter)
    }

    private fun listaProdotti(map: Map<String?, List<Float>?>) : List<ProdottoInListaModel>{
        val listaProdotti = mutableListOf<ProdottoInListaModel>()
        map.forEach { (key, value) ->
            val prodotto = key?.let { ProdottoInListaModel(it, value?.get(0) ?: 0.5f, value?.get(1) ?: 0f, value?.get(2) ?: 0f) }
            if (prodotto != null) {
                listaProdotti.add(prodotto)
            }
        }
        return listaProdotti
    }
}