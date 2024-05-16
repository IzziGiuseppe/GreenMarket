package com.example.greenmarket.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenmarket.db.model.Prodotto

@Dao
interface ProdottoDao {
    @Query("SELECT * FROM prodotto")
    fun getAll(): Array<Prodotto>

    @Query("SELECT * FROM prodotto WHERE nome = :nome")
    fun getProdottoByNome(nome: String): LiveData<Prodotto>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(vararg prodotto: Prodotto)

    @Update
    fun update(prodotto: Prodotto)

    @Delete
    fun delete(prodotto: Prodotto)
}