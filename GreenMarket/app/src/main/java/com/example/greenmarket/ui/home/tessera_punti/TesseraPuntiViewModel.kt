package com.example.greenmarket.ui.home.tessera_punti

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.greenmarket.db.model.CodiceSconto

class TesseraPuntiViewModel(application: Application): AndroidViewModel(application)  {

    private var _listaCodiciSconto= MutableLiveData(arrayOf<CodiceSconto>())
    val listaCodiciSconto: MutableLiveData<Array<CodiceSconto>>
        get() = _listaCodiciSconto

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