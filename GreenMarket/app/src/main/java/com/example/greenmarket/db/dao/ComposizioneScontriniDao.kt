package com.example.greenmarket.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenmarket.db.model.ComposizioneScontrini

interface ComposizioneScontriniDao {
    @Query("SELECT * FROM composizione_scontrini")
    fun getAll(): Array<ComposizioneScontrini>

    @Query("SELECT * FROM composizione_scontrini WHERE scontrino = :scontrino")
    fun getComposizioneScontrinoByScontrino(scontrino: Int): LiveData<ComposizioneScontrini>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(vararg composizione_scontrini: ComposizioneScontrini)

    @Update
    fun update(composizione_scontrini: ComposizioneScontrini)

    @Delete
    fun delete(composizione_scontrini: ComposizioneScontrini)
}