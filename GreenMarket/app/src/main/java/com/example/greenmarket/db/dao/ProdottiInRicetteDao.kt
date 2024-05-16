package com.example.greenmarket.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenmarket.db.model.ProdottiInRicette

interface ProdottiInRicetteDao {
    @Query("SELECT * FROM prodotti_in_ricette")
    fun getAll(): Array<ProdottiInRicette>

    @Query("SELECT * FROM prodotti_in_ricette WHERE ricetta = :ricetta")
    fun getProdottiInRicetteByRicetta(ricetta: String): LiveData<ProdottiInRicette>

    @Query("SELECT * FROM prodotti_in_ricette WHERE prodotto = :prodotto")
    fun getProdottiInRicetteByProdotto(prodotto: String): LiveData<ProdottiInRicette>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(vararg prodotti_in_ricette: ProdottiInRicette)

    @Update
    fun update(prodotti_in_ricette: ProdottiInRicette)

    @Delete
    fun delete(prodotti_in_ricette: ProdottiInRicette)
}