package com.example.greenmarket.ui.home.tessera_punti

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greenmarket.db.model.CodiceSconto
import com.example.greenmarket.ui.ricerca.ProdottoModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TesseraPuntiViewModel(application: Application): AndroidViewModel(application)  {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser

    private val _tessera_punti = MutableLiveData<TesseraPuntiModel>()
    val tessera_punti: LiveData<TesseraPuntiModel> = _tessera_punti

    private val _saldo = MutableLiveData<Float?>()
    val saldo: MutableLiveData<Float?> = _saldo

    private val _punti = MutableLiveData<Int?>()
    val punti: MutableLiveData<Int?> = _punti

    private var _listaCodiciSconto= MutableLiveData(arrayOf<CodiceSconto>())
    val listaCodiciSconto: MutableLiveData<Array<CodiceSconto>>
        get() = _listaCodiciSconto

    fun readTesseraPunti() {
        currentUser?.let {
            db.collection("users").document(it.uid).collection("pointCard").document("wallet").get()
                .addOnSuccessListener { document ->
                    _tessera_punti.value = document.toObject(TesseraPuntiModel::class.java)
                    _saldo.value = _tessera_punti.value?.saldo

                    _punti.value = _tessera_punti.value?.punti
                }
                .addOnFailureListener { exception ->
                    Log.e("Firebase", "Error getting point card details", exception)
                }
        }

    }

    fun readCodiciSconto() {
        val x = arrayOf(
            CodiceSconto("rbc3", 1, false),
            CodiceSconto("vqgvdufe", 1, false),
            CodiceSconto("bvduygef", 1, false),
            CodiceSconto("dyeuf", 1, false),
            CodiceSconto("bwefhue", 1, false),
            CodiceSconto("bhwvf rei", 1, false),
            CodiceSconto("vwgukv", 1, false),
            CodiceSconto("rhwgl", 1, false),
            CodiceSconto("gvetg", 1, false),
            CodiceSconto("ygetr", 1, false),
            CodiceSconto("greqvy", 1, false),
            CodiceSconto("grceh", 1, false),
            CodiceSconto("rcfa", 1, false),
            CodiceSconto("bfyuhe", 1, false),
            CodiceSconto("bhrwb", 1, false),
            CodiceSconto("cdtqyeidc", 1, false),
            CodiceSconto("gfey6ufi", 1, false),
            CodiceSconto("dgeqvd", 1, false),
            CodiceSconto("ccxzwudbc", 1, false),
            CodiceSconto("wvqdug", 1, false),
            CodiceSconto("dwquvd", 1, false)
        )
        _listaCodiciSconto.value = x
    }
}