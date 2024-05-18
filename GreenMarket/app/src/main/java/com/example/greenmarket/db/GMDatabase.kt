package com.example.greenmarket.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.greenmarket.db.dao.CodiceScontoDao
import com.example.greenmarket.db.dao.ComposizioneScontriniDao
import com.example.greenmarket.db.dao.ProdottiInRicetteDao
import com.example.greenmarket.db.dao.ProdottoDao
import com.example.greenmarket.db.dao.RicettaDao
import com.example.greenmarket.db.dao.ScontrinoDao
import com.example.greenmarket.db.dao.TesseraAPuntiDao
import com.example.greenmarket.db.dao.UtenteDao
import com.example.greenmarket.db.model.CodiceSconto
import com.example.greenmarket.db.model.ComposizioneScontrini
import com.example.greenmarket.db.model.ProdottiInRicette
import com.example.greenmarket.db.model.Prodotto
import com.example.greenmarket.db.model.Ricetta
import com.example.greenmarket.db.model.Scontrino
import com.example.greenmarket.db.model.TesseraAPunti
import com.example.greenmarket.db.model.Utente
import java.util.concurrent.Executors

@Database(entities = [Utente::class, TesseraAPunti::class, Scontrino::class, Ricetta::class, Prodotto::class,
    ProdottiInRicette::class, ComposizioneScontrini::class, CodiceSconto::class], version=2)
abstract class GMDatabase: RoomDatabase() {
    abstract fun UtenteDao(): UtenteDao
    abstract fun TesseraAPuntiDao(): TesseraAPuntiDao

    abstract fun ScontrinoDao(): ScontrinoDao

    abstract fun RicettaDao(): RicettaDao

    abstract fun ProdottoDao(): ProdottoDao

    abstract fun ProdottiInRicetteDao(): ProdottiInRicetteDao

    abstract fun ComposizioneScontriniDao(): ComposizioneScontriniDao

    abstract fun CodiceScontoDao(): CodiceScontoDao

    companion object {
        @Volatile
        private var INSTANCE: GMDatabase? = null

        fun getInstance(context: Context): GMDatabase {
            Log.d("GMDatabase", "ciao")
                return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    GMDatabase::class.java, "green_market_database"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }

        fun populateDatabase(context: Context) {
            Log.d("MYDATABASECALLBACK", "mydbcb3")
            Executors.newSingleThreadExecutor().execute {
                Log.d("MYDATABASECALLBACK", "mydbcb4")
                val database = getInstance(context)
                val productDao = database.ProdottoDao()

                Log.d("MYDATABASECALLBACK", "mydbcb5")
                // Inserisci i dati iniziali
                val product1 = Prodotto("Peperoni", "Sono peperoni", 2.99f, "", "kg")
                val product2 = Prodotto("Zucchine", "Sono Zucchine", 1.99f, "", "kg")
                Log.d("MYDATABASECALLBACK", "non inseriti")
                productDao.insert(product1, product2)
                Log.d("MYDATABASECALLBACK", "inseriti")
            }
        }
    }

}
