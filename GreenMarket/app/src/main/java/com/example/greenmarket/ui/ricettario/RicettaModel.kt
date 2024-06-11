package com.example.greenmarket.ui.ricettario

data class RicettaModel(
    var nome: String ?= null,
    var descrizione: String ?= null,
    var foto: String ?= null,
    var ingredienti: List<String> ?= null
)
