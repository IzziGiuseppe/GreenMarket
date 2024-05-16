package com.example.greenmarket.db.dao

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenmarket.db.model.Utente

@Dao
interface UtenteDao {
    @Query("SELECT * FROM utente")
    fun getAll(): Array<Utente>

    @Query("SELECT * FROM utente WHERE email = :email")
    fun getUtenteByEmail(email: String): LiveData<Utente>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(vararg utente: Utente)

    @Update
    fun update(utente: Utente)

    @Delete
    fun delete(utente: Utente)
}