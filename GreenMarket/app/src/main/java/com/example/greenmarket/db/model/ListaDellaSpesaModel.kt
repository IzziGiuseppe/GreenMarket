package com.example.greenmarket.db.model

import java.sql.Timestamp

data class ListaDellaSpesaModel(
    val data: Timestamp = Timestamp(0L),
    val valido: Boolean = false,
    val prodotti: Map<String?, List<Float>?> = emptyMap()
)
