package com.example.greenmarket.db.model

import java.sql.Timestamp

data class ScontrinoModel(
    val data: String = "",
    val valido: Boolean = false,
    val prodotti: Map<String?, List<Float>?> = emptyMap(),
    val totale: Float = 0.0f,
    val codiceSconto: String = "",
    val valoreSconto: String = "",
)
