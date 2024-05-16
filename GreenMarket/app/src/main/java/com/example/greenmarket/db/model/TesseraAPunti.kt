package com.example.greenmarket.db.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "tessera_a_punti", foreignKeys = [ForeignKey(entity = Utente::class,
    parentColumns = ["email"],
    childColumns = ["utente"],
    onDelete = ForeignKey.CASCADE)]
)
data class TesseraAPunti (
    @PrimaryKey(autoGenerate = true) val _id: Int,
    val saldo: Float,
    val punti: Int,
    val utente: String
)