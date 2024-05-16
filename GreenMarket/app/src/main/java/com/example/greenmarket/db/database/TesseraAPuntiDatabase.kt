package com.example.greenmarket.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.greenmarket.db.dao.TesseraAPuntiDao
import com.example.greenmarket.db.model.TesseraAPunti

@Database(entities = [TesseraAPunti::class], version=1)
abstract class TesseraAPuntiDatabase: RoomDatabase() {
    abstract fun TesseraAPuntiDao(): TesseraAPuntiDao

    companion object {
        @Volatile
        private var INSTANCE: TesseraAPuntiDatabase? = null

        fun getInstance(context: Context): TesseraAPuntiDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TesseraAPuntiDatabase::class.java, "tessera_a_punti_database"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }

}