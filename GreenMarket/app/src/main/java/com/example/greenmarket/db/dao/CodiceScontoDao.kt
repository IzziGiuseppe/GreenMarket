package com.example.greenmarket.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenmarket.db.model.CodiceSconto

interface CodiceScontoDao {
    @Query("SELECT * FROM codice_sconto")
    fun getAll(): Array<CodiceSconto>

    @Query("SELECT * FROM codice_sconto WHERE codice = :codice")
    fun getCodiceScontoByCodice(codice: String): LiveData<CodiceSconto>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(vararg codice_sconto: CodiceSconto)

    @Update
    fun update(codice_sconto: CodiceSconto)

    @Delete
    fun delete(codice_sconto: CodiceSconto)
}