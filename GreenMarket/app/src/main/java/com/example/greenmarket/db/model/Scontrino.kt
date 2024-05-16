package com.example.greenmarket.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import java.util.Date

@Entity(tableName = "scontrino", foreignKeys = [ForeignKey(entity = Utente::class,
    parentColumns = ["email"],
    childColumns = ["utente"],
    onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = CodiceSconto::class,
        parentColumns = ["codice"],
        childColumns = ["codice_sconto"],
        onDelete = ForeignKey.NO_ACTION)]
)
data class Scontrino (
    @PrimaryKey(autoGenerate = true) val _id: Int,
    val data: Date,
    val utente: String,
    val codice_sconto: String,
    val valido: Boolean
)

