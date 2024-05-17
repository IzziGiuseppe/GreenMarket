package com.example.greenmarket.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prodotto")
data class Prodotto(
    @PrimaryKey val nome: String,
    val descrzione: String,
    val prezzo: Float,
    val foto: String,
    val unita_di_misura: String
)