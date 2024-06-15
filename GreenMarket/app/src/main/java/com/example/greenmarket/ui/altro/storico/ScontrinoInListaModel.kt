package com.example.greenmarket.ui.altro.storico

import java.sql.Timestamp

data class ScontrinoInListaModel(
    val data: Timestamp = Timestamp(0L),
    val totale: Int = 0,
    val numProdotti: Int = 0
)
