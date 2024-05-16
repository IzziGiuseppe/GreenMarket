package com.example.greenmarket.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenmarket.db.model.Scontrino

interface ScontrinoDao {
    @Query("SELECT * FROM scontrino")
    fun getAll(): Array<Scontrino>

    @Query("SELECT * FROM scontrino WHERE _id = :id")
    fun getScontrinoById(id: Int): LiveData<Scontrino>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(vararg scontrino: Scontrino)

    @Update
    fun update(scontrino: Scontrino)

    @Delete
    fun delete(scontrino: Scontrino)
}