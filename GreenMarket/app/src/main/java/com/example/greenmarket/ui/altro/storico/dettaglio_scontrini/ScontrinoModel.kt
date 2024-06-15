package com.example.greenmarket.ui.altro.storico.dettaglio_scontrini

import java.sql.Timestamp

data class ScontrinoModel(
    val data: String = "",
    val valido: Boolean = false,
    val prodotti: Map<String?, List<Float>?> = emptyMap(),
    val totale: Float = 0.0f
)
