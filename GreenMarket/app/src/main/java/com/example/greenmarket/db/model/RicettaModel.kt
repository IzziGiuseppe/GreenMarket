package com.example.greenmarket.db.model

data class RicettaModel(
    var nome: String ?= null,
    var descrizione: String ?= null,
    var foto: String ?= null,
    var ingredienti: List<String> ?= null
)
