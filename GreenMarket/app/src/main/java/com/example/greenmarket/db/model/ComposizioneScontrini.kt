package com.example.greenmarket.db.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "composizione_scontrini", primaryKeys = ["scontrino", "prodotto"], foreignKeys = [ForeignKey(entity = Scontrino::class,
    parentColumns = ["_id"],
    childColumns = ["scontrino"],
    onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Prodotto::class,
        parentColumns = ["nome"],
        childColumns = ["prodotto"],
        onDelete = ForeignKey.NO_ACTION)]
)
data class ComposizioneScontrini (
    val scontrino: Int,
    val prodotto: String,
    val quantita: Float
)