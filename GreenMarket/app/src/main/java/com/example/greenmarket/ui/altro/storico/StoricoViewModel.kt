package com.example.greenmarket.ui.altro.storico

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greenmarket.db.model.ScontrinoModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class StoricoViewModel(application: Application): AndroidViewModel(application) {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser

    private val _text = MutableLiveData<String>().apply {
        value = "Storico scontrini"
    }
    val text: LiveData<String> = _text

    private var _lista_scontrini = MutableLiveData<List<ScontrinoModel>>()
    val lista_scontrini: MutableLiveData<List<ScontrinoModel>>
        get() = _lista_scontrini

    private var _scontrino = MutableLiveData<ScontrinoModel>()
    val scontrino: MutableLiveData<ScontrinoModel>
        get() = _scontrino

    fun readScontrini() {
        currentUser?.let {
            db.collection("users").document(it.uid).collection("historical").whereEqualTo("valido", true).get()
                .addOnSuccessListener { documents ->
                    _lista_scontrini.value = documents.toObjects(ScontrinoModel::class.java).sortedByDescending { it.data }
                }
                .addOnFailureListener { exception ->
                    Log.e("Firebase", "Error getting product details", exception)
                }
        }
    }

    fun readScontrinoDettagliato(data: String){
        for(i in _lista_scontrini.value!!){
            if(i.data == data){
                _scontrino.value = i
            }
        }
    }

    fun resetScontrino() {
        _scontrino.value = ScontrinoModel()
    }

}