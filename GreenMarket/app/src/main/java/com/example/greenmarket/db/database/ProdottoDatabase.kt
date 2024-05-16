package com.example.greenmarket.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.greenmarket.db.dao.ProdottoDao
import com.example.greenmarket.db.model.Prodotto

@Database(entities = [Prodotto::class], version=1)
abstract class ProdottoDatabase: RoomDatabase() {
    abstract fun ProdottoDao(): ProdottoDao

    companion object {
        @Volatile
        private var INSTANCE: ProdottoDatabase? = null

        fun getInstance(context: Context): ProdottoDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ProdottoDatabase::class.java, "prodotto_database"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }

}