package com.example.greenmarket.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenmarket.db.model.ProdottiInRicette

@Dao
interface ProdottiInRicetteDao {
    @Query("SELECT * FROM prodotti_in_ricette WHERE (Ricetta, Prodotto) IN (SELECT Ricetta, MIN(Prodotto) FROM prodotti_in_ricette GROUP BY Ricetta);")
    fun getAll(): Array<ProdottiInRicette>

    @Query("SELECT * FROM prodotti_in_ricette WHERE ricetta = :ricetta")
    fun getProdottiInRicetteByRicetta(ricetta: String): LiveData<ProdottiInRicette>

    @Query("SELECT * FROM prodotti_in_ricette WHERE prodotto = :prodotto")
    fun getProdottiInRicetteByProdotto(prodotto: String): Array<ProdottiInRicette>
            //LiveData<ProdottiInRicette>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg prodotti_in_ricette: ProdottiInRicette)

    @Update
    fun update(prodotti_in_ricette: ProdottiInRicette)

    @Delete
    fun delete(prodotti_in_ricette: ProdottiInRicette)

    @Query("DELETE FROM prodotti_in_ricette")
    fun deleteAllProdottiInRicette()
}