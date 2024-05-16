package com.example.greenmarket.db.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "prodotti_in_ricette", primaryKeys = ["ricetta", "prodotto"], foreignKeys = [ForeignKey(entity = Ricetta::class,
    parentColumns = ["nome"],
    childColumns = ["ricetta"],
    onDelete = ForeignKey.NO_ACTION),
    ForeignKey(entity = Prodotto::class,
        parentColumns = ["nome"],
        childColumns = ["prodotto"],
        onDelete = ForeignKey.NO_ACTION)]
)
data class ProdottiInRicette (
    val ricetta: String,
    val prodotto: String
)