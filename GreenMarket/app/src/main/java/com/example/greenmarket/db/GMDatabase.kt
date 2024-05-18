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
    ProdottiInRicette::class, ComposizioneScontrini::class, CodiceSconto::class], version=3)
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
            Executors.newSingleThreadExecutor().execute {
                val database = getInstance(context)
                val productDao = database.ProdottoDao()

                // Inserisci i dati iniziali
                val product1 = Prodotto("Mele", "Sono mele", 2.99f, "", "kg")
                val product2 = Prodotto("Pere", "Sono pere", 1.99f, "", "kg")
                val product3 = Prodotto("Banane", "Sono banane", 2.99f, "", "kg")
                val product4 = Prodotto("Arance", "Sono arance", 1.99f, "", "kg")
                val product5 = Prodotto("Susine", "Sono susine", 2.99f, "", "kg")
                val product6 = Prodotto("Melanzane", "Sono melanzane", 1.99f, "", "kg")
                val product7 = Prodotto("Zucchine", "Sono zucchine", 2.99f, "", "kg")
                val product8 = Prodotto("Funghi", "Sono funghi", 1.99f, "", "kg")
                productDao.insert(product1, product2, product3, product4, product5, product6, product7, product8)

                val ricettaDao = database.RicettaDao()

                // Inserisci i dati iniziali
                val ricetta1 = Ricetta("Pasta al sugo", "Il pranzo della domenica", "")
                val ricetta2 = Ricetta("Pasta e zucchine", "Molto buono", "")
                val ricetta3 = Ricetta("Pasta e melanzane", "Molto buono", "")
                val ricetta4 = Ricetta("Pasta e verdure", "Molto buono", "")
                val ricetta5 = Ricetta("Pasta e fagioli", "Molto buono", "")
                val ricetta6 = Ricetta("Pasta e ceci", "Molto buono", "")
                val ricetta7 = Ricetta("Pasta al pesto", "Molto buono", "")
                val ricetta8 = Ricetta("Pasta e piselli", "Molto buono", "")
                ricettaDao.insert(ricetta1, ricetta2, ricetta3, ricetta4, ricetta5, ricetta6, ricetta7, ricetta8)
            }
        }


    }

}
