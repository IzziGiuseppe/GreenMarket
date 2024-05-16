package com.example.greenmarket.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "utente")
data class Utente (
    @PrimaryKey val email: String,
    val password: String,
    val nome: String,
    val cognome: String,
    val indirizzo: String
)