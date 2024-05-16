package com.example.greenmarket.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.greenmarket.db.dao.ComposizioneScontriniDao
import com.example.greenmarket.db.model.ComposizioneScontrini

@Database(entities = [ComposizioneScontrini::class], version=1)
abstract class ComposizioneScontriniDatabase: RoomDatabase() {
    abstract fun ComposizioneScontriniDao(): ComposizioneScontriniDao

    companion object {
        @Volatile
        private var INSTANCE: ComposizioneScontriniDatabase? = null

        fun getInstance(context: Context): ComposizioneScontriniDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ComposizioneScontriniDatabase::class.java, "composizione_scontrini_database"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }

}