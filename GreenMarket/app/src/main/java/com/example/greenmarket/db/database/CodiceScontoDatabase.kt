package com.example.greenmarket.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.greenmarket.db.dao.CodiceScontoDao
import com.example.greenmarket.db.model.CodiceSconto

@Database(entities = [CodiceSconto::class], version=1)
abstract class CodiceScontoDatabase: RoomDatabase() {
    abstract fun CodiceScontoDao(): CodiceScontoDao

    companion object {
        @Volatile
        private var INSTANCE: CodiceScontoDatabase? = null

        fun getInstance(context: Context): CodiceScontoDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    CodiceScontoDatabase::class.java, "codice_sconto_database"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }

}