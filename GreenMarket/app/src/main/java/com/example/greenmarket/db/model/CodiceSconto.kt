package com.example.greenmarket.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "codice_sconto", foreignKeys = [ForeignKey(entity = TesseraAPunti::class,
    parentColumns = ["_id"],
    childColumns = ["tessera_a_punti"],
    onDelete = ForeignKey.CASCADE)]
)
data class CodiceSconto (
    @PrimaryKey val codice: String,
    val tessera_a_punti: Int,
    val utilizzato: Boolean
)