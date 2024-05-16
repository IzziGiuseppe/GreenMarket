package com.example.greenmarket.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.greenmarket.db.dao.ProdottiInRicetteDao
import com.example.greenmarket.db.model.ProdottiInRicette

@Database(entities = [ProdottiInRicette::class], version=1)
abstract class ProdottiInRicetteDatabase: RoomDatabase() {
    abstract fun ProdottiInRicetteDao(): ProdottiInRicetteDao

    companion object {
        @Volatile
        private var INSTANCE: ProdottiInRicetteDatabase? = null

        fun getInstance(context: Context): ProdottiInRicetteDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ProdottiInRicetteDatabase::class.java, "prodotti_in_ricette_database"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }

}