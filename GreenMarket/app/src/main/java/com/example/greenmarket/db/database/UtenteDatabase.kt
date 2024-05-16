package com.example.greenmarket.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.greenmarket.db.dao.UtenteDao
import com.example.greenmarket.db.model.Utente

@Database(entities = [Utente::class], version=1)
abstract class UtenteDatabase: RoomDatabase() {
    abstract fun UtenteDao(): UtenteDao

    companion object {
        @Volatile
        private var INSTANCE: UtenteDatabase? = null

        fun getInstance(context: Context): UtenteDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UtenteDatabase::class.java, "utente_database"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }

}