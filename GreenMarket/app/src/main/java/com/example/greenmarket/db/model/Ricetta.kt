package com.example.greenmarket.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ricetta")
data class Ricetta (
    @PrimaryKey val nome: String,
    val descrzione: String,
    val prezzo: String,
    val foto: String
)