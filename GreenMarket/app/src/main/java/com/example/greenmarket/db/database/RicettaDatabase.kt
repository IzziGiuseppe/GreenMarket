package com.example.greenmarket.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.greenmarket.db.dao.RicettaDao
import com.example.greenmarket.db.model.Ricetta

@Database(entities = [Ricetta::class], version=1)
abstract class RicettaDatabase: RoomDatabase() {
    abstract fun RicettaDao(): RicettaDao

    companion object {
        @Volatile
        private var INSTANCE: RicettaDatabase? = null

        fun getInstance(context: Context): RicettaDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RicettaDatabase::class.java, "ricetta_database"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }

}