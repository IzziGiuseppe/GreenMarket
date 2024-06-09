package com.example.greenmarket.ui.lista_spesa

import com.google.firebase.Timestamp

data class ListaDellaSpesaModel(
    val data: Timestamp = Timestamp.now(),
    val valido: Boolean = false,
    val prodotto: Map<String?, ProdottoInListaModel?> = emptyMap()
)
