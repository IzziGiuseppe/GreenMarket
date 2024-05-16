package com.example.greenmarket.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenmarket.db.model.Ricetta

interface RicettaDao {
    @Query("SELECT * FROM ricetta")
    fun getAll(): Array<Ricetta>

    @Query("SELECT * FROM ricetta WHERE nome = :nome")
    fun getRicettaByNome(nome: String): LiveData<Ricetta>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(vararg ricetta: Ricetta)

    @Update
    fun update(ricetta: Ricetta)

    @Delete
    fun delete(ricetta: Ricetta)
}