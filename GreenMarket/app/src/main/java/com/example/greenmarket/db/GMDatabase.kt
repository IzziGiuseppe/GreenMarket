package com.example.greenmarket.db

import android.content.Context
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
                val ricettaDao = database.RicettaDao()

                val creationGetting = CreazioneOggetti()

                val prodotti = creationGetting.getProdotti()
                val ricette = creationGetting.getRicette()
                val prodottiInRicette = creationGetting.getProdInRicette()

                for (prodotto in prodotti) {
                    productDao.insert(prodotto)
                }

                for (ricetta in ricette) {
                    ricettaDao.insert(ricetta)
                }

                for (pR in prodottiInRicette) {
                    pRDao.insert(pR)
                }

            }
        }


    }

}
