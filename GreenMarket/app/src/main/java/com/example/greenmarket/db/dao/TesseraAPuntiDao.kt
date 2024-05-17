package com.example.greenmarket.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenmarket.db.model.TesseraAPunti

@Dao
interface TesseraAPuntiDao {
    @Query("SELECT * FROM tessera_a_punti")
    fun getAll(): Array<TesseraAPunti>

    @Query("SELECT * FROM tessera_a_punti WHERE _id = :id")
    fun getTesseraAPuntiById(id: Int): LiveData<TesseraAPunti>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(vararg tesseraAPunti: TesseraAPunti)

    @Update
    fun update(tesseraAPunti: TesseraAPunti)

    @Delete
    fun delete(tesseraAPunti: TesseraAPunti)
}