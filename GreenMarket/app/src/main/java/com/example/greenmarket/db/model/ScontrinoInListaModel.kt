package com.example.greenmarket.db.model

import java.sql.Timestamp

data class ScontrinoInListaModel(
    val data: Timestamp = Timestamp(0L),
    val totale: Int = 0,
    val numProdotti: Int = 0
)
