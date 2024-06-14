package com.example.greenmarket.ui.ricerca.dettaglio_prodotti

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greenmarket.ui.lista_spesa.ListaDellaSpesaModel
import com.example.greenmarket.ui.lista_spesa.ProdottoInListaModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.math.floor

class DettaglioProdottiViewModel(application: Application): AndroidViewModel(application) {

    //Inizializzo il db
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    //Inizializzo l'utente
    private val currentUser = FirebaseAuth.getInstance().currentUser

    private var _nome_prodotto = MutableLiveData<String>().apply {
        value = "Nome prodotto"
    }
    val nome_prodotto: LiveData<String> = _nome_prodotto

    private var _descrizione_prodotto = MutableLiveData<String>().apply {
        value = "Descrizione prodotto"
    }
    val descrizione_prodotto: LiveData<String> = _descrizione_prodotto

    private var _foto_prodotto = MutableLiveData<String>().apply {
        value = "Foto prodotto"
    }
    val foto_prodotto: LiveData<String> = _foto_prodotto

    private var _prezzo_prodotto = MutableLiveData<Float>().apply {
        value = 0f
    }
    val prezzo_prodotto: LiveData<Float> = _prezzo_prodotto

    private var _uM_prodotto = MutableLiveData<String>().apply {
        value = "Unità di misura prodotto"
    }
    val uM_prodotto: LiveData<String> = _uM_prodotto

    private var _quantita_prodotto = MutableLiveData<Float>().apply {
        value = 0.5f
    }
    val quantita_prodotto: LiveData<Float> = _quantita_prodotto

    private var _prezzo_totale = MutableLiveData<Float>().apply {
        value = 0f
    }
    val prezzo_totale: LiveData<Float> = _prezzo_totale

    fun setNome(nome: String) {
        _nome_prodotto.value = nome
    }

    fun setDescrizione(descrizione: String) {
        _descrizione_prodotto.value = descrizione
    }

    fun setFoto(foto: String) {
        _foto_prodotto.value = foto
    }

    fun setPrezzo(prezzo: Float) {
        _prezzo_prodotto.value = prezzo
    }

    fun setUM(uM: String) {
        _uM_prodotto.value = uM
    }

    fun setQuantita(quantita: Float){
        _quantita_prodotto.value = quantita
    }

    fun incrementaQuantita() {
        if(quantita_prodotto.value!! < 100){
            _quantita_prodotto.value = _quantita_prodotto.value?.plus(0.5f)
        }else{
            Toast.makeText(getApplication(), "La quantità non può superare 100.0 kg", Toast.LENGTH_SHORT).show()
        }

    }

    fun decrementaQuantita() {
        if(quantita_prodotto.value!! > 0){
            _quantita_prodotto.value = _quantita_prodotto.value?.minus(0.5f)
        }else{
            Toast.makeText(getApplication(), "La quantità non può essere negativa", Toast.LENGTH_SHORT).show()
        }

    }

    fun inserimentoProdottoInListaSpesa() {
        _prezzo_totale.value = _quantita_prodotto.value?.let { quantita ->
            _prezzo_prodotto.value?.let { prezzo ->
                val totale = quantita * prezzo
                // Arrotondare per difetto alle prime due cifre decimali
                (floor(totale * 100) / 100)
            }
        }
        val infoProdotto = listOf(
            _quantita_prodotto.value,
            _prezzo_prodotto.value,
            _prezzo_totale.value
        )

        val updates = mapOf(
            "prodotti.${_nome_prodotto.value}" to infoProdotto
        )

        currentUser?.let {
            db.collection("users").document(it.uid).collection("historical").document("shoppingList").update(updates)
                .addOnSuccessListener {
                    Toast.makeText(getApplication(), "Prodotto aggiunto alla Lista della spesa", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(getApplication(), "Errore: Prodotto non aggiunto alla Lista della spesa", Toast.LENGTH_SHORT).show()
                }
        }
    }

}