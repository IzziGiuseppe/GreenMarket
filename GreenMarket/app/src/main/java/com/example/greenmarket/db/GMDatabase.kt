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
                val pRDao = database.ProdottiInRicetteDao()

                // Inserisci i dati iniziali
                val product1 = Prodotto("Mele", "Sono mele", 2.99f, "", "kg")
                val product2 = Prodotto("Pere", "Sono pere", 1.99f, "", "kg")
                val product3 = Prodotto("Banane", "Sono banane", 2.99f, "", "kg")
                val product4 = Prodotto("Arance", "Sono arance", 1.99f, "", "kg")
                val product5 = Prodotto("Susine", "Sono susine", 2.99f, "", "kg")
                val product6 = Prodotto("Melanzane", "Sono melanzane", 1.99f, "", "kg")
                val product7 = Prodotto("Zucchine", "Sono zucchine", 2.99f, "", "kg")
                val product8 = Prodotto("Funghi", "Sono funghi", 1.99f, "", "kg")
                val product9 = Prodotto("Salsa di pomodoro", "Sono salsa di pomodoro", 2.99f, "", "kg")
                val product10 = Prodotto("Rape", "Sono rape", 1.99f, "", "kg")
                val product11 = Prodotto("Cicorie", "Sono cicorie", 2.99f, "", "kg")
                val product12 = Prodotto("Bietole", "Sono bietole", 1.99f, "", "kg")
                val product13 = Prodotto("Fagioli", "Sono fagioli", 2.99f, "", "kg")
                val product14 = Prodotto("Ceci", "Sono ceci", 1.99f, "", "kg")
                val product15 = Prodotto("Basilico", "Sono basilico", 2.99f, "", "kg")
                val product16 = Prodotto("Piselli", "Sono piselli", 1.99f, "", "kg")
                val product17 = Prodotto("Guanciale", "Sono guanciale", 2.99f, "", "kg")
                val product18 = Prodotto("Uovo", "Sono uovo", 1.99f, "", "kg")
                val product19 = Prodotto("Vongole", "Sono vongole", 2.99f, "", "kg")
                val product20 = Prodotto("Patate", "Sono patate", 1.99f, "", "kg")
                val product21 = Prodotto("Pane", "Sono pane", 2.99f, "", "kg")
                val product22 = Prodotto("Pomodori", "Sono pomodori", 1.99f, "", "kg")
                productDao.insert(product1, product2, product3, product4, product5, product6, product7, product8)
                productDao.insert(product9, product10, product11, product12, product13, product14, product15,
                    product16, product17, product18, product19, product20, product21, product22)

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

                val ricetta9 = Ricetta("Parmigiana", "Molto buono", "")
                val ricetta10 = Ricetta("Carbonara", "Molto buono", "")
                val ricetta11 = Ricetta("Spaghetti allo scoglio", "Molto buono", "")
                val ricetta12 = Ricetta("Pan cott", "Molto buono", "")
                val ricetta13 = Ricetta("Pane e pomodoro", "Molto buono", "")
                val ricetta14 = Ricetta("Amatriciana", "Molto buono", "")
                val ricetta15 = Ricetta("Lasagna bianca", "Molto buono", "")
                val ricetta16 = Ricetta("Pasta e patate", "Molto buono", "")
                ricettaDao.insert(ricetta9, ricetta10, ricetta11, ricetta12, ricetta13, ricetta14, ricetta15, ricetta16)

                //Inserimento prodotti in ricette
                val pr1 = ProdottiInRicette("Pasta al sugo", "Salsa di pomodoro")
                val pr2 = ProdottiInRicette("Pasta e zucchine", "Zucchine")
                val pr3 = ProdottiInRicette("Pasta e melanzane", "Melanzane")
                val pr4 = ProdottiInRicette("Pasta e verdure", "Rape")
                val pr5 = ProdottiInRicette("Pasta e verdure", "Cicorie")
                val pr6 = ProdottiInRicette("Pasta e verdure", "Bietole")
                val pr7 = ProdottiInRicette("Pasta e fagioli", "Fagioli")
                val pr8 = ProdottiInRicette("Pasta e ceci", "Ceci")
                val pr9 = ProdottiInRicette("Pasta al pesto", "Basilico")
                val pr10 = ProdottiInRicette("Pasta e piselli", "Piselli")
                val pr11 = ProdottiInRicette("Parmigiana", "Melanzane")
                val pr12 = ProdottiInRicette("Carbonara", "Guanciale")
                val pr13 = ProdottiInRicette("Carbonara", "Uovo")
                val pr14 = ProdottiInRicette("Spaghetti allo scoglio", "Vongole")
                val pr15 = ProdottiInRicette("Pan cott", "Cicorie")
                val pr16 = ProdottiInRicette("Pan cott", "Patate")
                val pr17 = ProdottiInRicette("Pan cott", "Pane")
                val pr18 = ProdottiInRicette("Pane e pomodoro", "Pomodori")
                val pr19 = ProdottiInRicette("Amatriciana", "Guanciale")
                val pr20 = ProdottiInRicette("Pasta e patate", "Patate")
                pRDao.insert(pr1, pr2, pr3, pr4, pr5, pr6, pr7, pr8, pr9, pr10,
                    pr11, pr12, pr13, pr14, pr15, pr16, pr17, pr18, pr19, pr20)
            }
        }


    }

}
