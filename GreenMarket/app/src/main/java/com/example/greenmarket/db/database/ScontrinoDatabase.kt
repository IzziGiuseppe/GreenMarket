package com.example.greenmarket.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.greenmarket.db.dao.ScontrinoDao
import com.example.greenmarket.db.model.Scontrino

@Database(entities = [Scontrino::class], version=1)
abstract class ScontrinoDatabase: RoomDatabase() {
    abstract fun ScontrinoDao(): ScontrinoDao

    companion object {
        @Volatile
        private var INSTANCE: ScontrinoDatabase? = null

        fun getInstance(context: Context): ScontrinoDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ScontrinoDatabase::class.java, "scontrino_database"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }

}