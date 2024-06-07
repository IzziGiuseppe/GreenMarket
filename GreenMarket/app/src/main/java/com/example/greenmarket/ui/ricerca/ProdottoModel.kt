package com.example.greenmarket.ui.ricerca

data class ProdottoModel(
    var nome: String ?= null,
    var descrizione: String ?= null,
    var prezzo: Float ?= null,
    var foto: String ?= null,
    var unita_di_misura: String ?= null
)
