package com.example.greenmarket.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.greenmarket.db.dao.CodiceScontoDao;
import com.example.greenmarket.db.dao.CodiceScontoDao_Impl;
import com.example.greenmarket.db.dao.ComposizioneScontriniDao;
import com.example.greenmarket.db.dao.ComposizioneScontriniDao_Impl;
import com.example.greenmarket.db.dao.ProdottiInRicetteDao;
import com.example.greenmarket.db.dao.ProdottiInRicetteDao_Impl;
import com.example.greenmarket.db.dao.ProdottoDao;
import com.example.greenmarket.db.dao.ProdottoDao_Impl;
import com.example.greenmarket.db.dao.RicettaDao;
import com.example.greenmarket.db.dao.RicettaDao_Impl;
import com.example.greenmarket.db.dao.ScontrinoDao;
import com.example.greenmarket.db.dao.ScontrinoDao_Impl;
import com.example.greenmarket.db.dao.TesseraAPuntiDao;
import com.example.greenmarket.db.dao.TesseraAPuntiDao_Impl;
import com.example.greenmarket.db.dao.UtenteDao;
import com.example.greenmarket.db.dao.UtenteDao_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class GMDatabase_Impl extends GMDatabase {
  private volatile UtenteDao _utenteDao;

  private volatile TesseraAPuntiDao _tesseraAPuntiDao;

  private volatile ScontrinoDao _scontrinoDao;

  private volatile RicettaDao _ricettaDao;

  private volatile ProdottoDao _prodottoDao;

  private volatile ProdottiInRicetteDao _prodottiInRicetteDao;

  private volatile ComposizioneScontriniDao _composizioneScontriniDao;

  private volatile CodiceScontoDao _codiceScontoDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `utente` (`email` TEXT NOT NULL, `password` TEXT NOT NULL, `nome` TEXT NOT NULL, `cognome` TEXT NOT NULL, `indirizzo` TEXT NOT NULL, PRIMARY KEY(`email`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `tessera_a_punti` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `saldo` REAL NOT NULL, `punti` INTEGER NOT NULL, `utente` TEXT NOT NULL, FOREIGN KEY(`utente`) REFERENCES `utente`(`email`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `scontrino` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `data` TEXT NOT NULL, `utente` TEXT NOT NULL, `codice_sconto` TEXT NOT NULL, `valido` INTEGER NOT NULL, FOREIGN KEY(`utente`) REFERENCES `utente`(`email`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`codice_sconto`) REFERENCES `codice_sconto`(`codice`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ricetta` (`nome` TEXT NOT NULL, `descrizione` TEXT NOT NULL, `foto` TEXT NOT NULL, PRIMARY KEY(`nome`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `prodotto` (`nome` TEXT NOT NULL, `descrizione` TEXT NOT NULL, `prezzo` REAL NOT NULL, `foto` TEXT NOT NULL, `unita_di_misura` TEXT NOT NULL, PRIMARY KEY(`nome`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `prodotti_in_ricette` (`ricetta` TEXT NOT NULL, `prodotto` TEXT NOT NULL, PRIMARY KEY(`ricetta`, `prodotto`), FOREIGN KEY(`ricetta`) REFERENCES `ricetta`(`nome`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`prodotto`) REFERENCES `prodotto`(`nome`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `composizione_scontrini` (`scontrino` INTEGER NOT NULL, `prodotto` TEXT NOT NULL, `quantita` REAL NOT NULL, PRIMARY KEY(`scontrino`, `prodotto`), FOREIGN KEY(`scontrino`) REFERENCES `scontrino`(`_id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`prodotto`) REFERENCES `prodotto`(`nome`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `codice_sconto` (`codice` TEXT NOT NULL, `tessera_a_punti` INTEGER NOT NULL, `utilizzato` INTEGER NOT NULL, PRIMARY KEY(`codice`), FOREIGN KEY(`tessera_a_punti`) REFERENCES `tessera_a_punti`(`_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '3392d83a8bbed8031cdcf01b6b7ebc2c')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `utente`");
        _db.execSQL("DROP TABLE IF EXISTS `tessera_a_punti`");
        _db.execSQL("DROP TABLE IF EXISTS `scontrino`");
        _db.execSQL("DROP TABLE IF EXISTS `ricetta`");
        _db.execSQL("DROP TABLE IF EXISTS `prodotto`");
        _db.execSQL("DROP TABLE IF EXISTS `prodotti_in_ricette`");
        _db.execSQL("DROP TABLE IF EXISTS `composizione_scontrini`");
        _db.execSQL("DROP TABLE IF EXISTS `codice_sconto`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsUtente = new HashMap<String, TableInfo.Column>(5);
        _columnsUtente.put("email", new TableInfo.Column("email", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUtente.put("password", new TableInfo.Column("password", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUtente.put("nome", new TableInfo.Column("nome", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUtente.put("cognome", new TableInfo.Column("cognome", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUtente.put("indirizzo", new TableInfo.Column("indirizzo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUtente = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUtente = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUtente = new TableInfo("utente", _columnsUtente, _foreignKeysUtente, _indicesUtente);
        final TableInfo _existingUtente = TableInfo.read(_db, "utente");
        if (! _infoUtente.equals(_existingUtente)) {
          return new RoomOpenHelper.ValidationResult(false, "utente(com.example.greenmarket.db.model.Utente).\n"
                  + " Expected:\n" + _infoUtente + "\n"
                  + " Found:\n" + _existingUtente);
        }
        final HashMap<String, TableInfo.Column> _columnsTesseraAPunti = new HashMap<String, TableInfo.Column>(4);
        _columnsTesseraAPunti.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTesseraAPunti.put("saldo", new TableInfo.Column("saldo", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTesseraAPunti.put("punti", new TableInfo.Column("punti", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTesseraAPunti.put("utente", new TableInfo.Column("utente", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTesseraAPunti = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysTesseraAPunti.add(new TableInfo.ForeignKey("utente", "CASCADE", "NO ACTION",Arrays.asList("utente"), Arrays.asList("email")));
        final HashSet<TableInfo.Index> _indicesTesseraAPunti = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTesseraAPunti = new TableInfo("tessera_a_punti", _columnsTesseraAPunti, _foreignKeysTesseraAPunti, _indicesTesseraAPunti);
        final TableInfo _existingTesseraAPunti = TableInfo.read(_db, "tessera_a_punti");
        if (! _infoTesseraAPunti.equals(_existingTesseraAPunti)) {
          return new RoomOpenHelper.ValidationResult(false, "tessera_a_punti(com.example.greenmarket.db.model.TesseraAPunti).\n"
                  + " Expected:\n" + _infoTesseraAPunti + "\n"
                  + " Found:\n" + _existingTesseraAPunti);
        }
        final HashMap<String, TableInfo.Column> _columnsScontrino = new HashMap<String, TableInfo.Column>(5);
        _columnsScontrino.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScontrino.put("data", new TableInfo.Column("data", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScontrino.put("utente", new TableInfo.Column("utente", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScontrino.put("codice_sconto", new TableInfo.Column("codice_sconto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScontrino.put("valido", new TableInfo.Column("valido", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysScontrino = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysScontrino.add(new TableInfo.ForeignKey("utente", "CASCADE", "NO ACTION",Arrays.asList("utente"), Arrays.asList("email")));
        _foreignKeysScontrino.add(new TableInfo.ForeignKey("codice_sconto", "NO ACTION", "NO ACTION",Arrays.asList("codice_sconto"), Arrays.asList("codice")));
        final HashSet<TableInfo.Index> _indicesScontrino = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoScontrino = new TableInfo("scontrino", _columnsScontrino, _foreignKeysScontrino, _indicesScontrino);
        final TableInfo _existingScontrino = TableInfo.read(_db, "scontrino");
        if (! _infoScontrino.equals(_existingScontrino)) {
          return new RoomOpenHelper.ValidationResult(false, "scontrino(com.example.greenmarket.db.model.Scontrino).\n"
                  + " Expected:\n" + _infoScontrino + "\n"
                  + " Found:\n" + _existingScontrino);
        }
        final HashMap<String, TableInfo.Column> _columnsRicetta = new HashMap<String, TableInfo.Column>(3);
        _columnsRicetta.put("nome", new TableInfo.Column("nome", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRicetta.put("descrizione", new TableInfo.Column("descrizione", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRicetta.put("foto", new TableInfo.Column("foto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRicetta = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRicetta = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRicetta = new TableInfo("ricetta", _columnsRicetta, _foreignKeysRicetta, _indicesRicetta);
        final TableInfo _existingRicetta = TableInfo.read(_db, "ricetta");
        if (! _infoRicetta.equals(_existingRicetta)) {
          return new RoomOpenHelper.ValidationResult(false, "ricetta(com.example.greenmarket.db.model.Ricetta).\n"
                  + " Expected:\n" + _infoRicetta + "\n"
                  + " Found:\n" + _existingRicetta);
        }
        final HashMap<String, TableInfo.Column> _columnsProdotto = new HashMap<String, TableInfo.Column>(5);
        _columnsProdotto.put("nome", new TableInfo.Column("nome", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProdotto.put("descrizione", new TableInfo.Column("descrizione", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProdotto.put("prezzo", new TableInfo.Column("prezzo", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProdotto.put("foto", new TableInfo.Column("foto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProdotto.put("unita_di_misura", new TableInfo.Column("unita_di_misura", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProdotto = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProdotto = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProdotto = new TableInfo("prodotto", _columnsProdotto, _foreignKeysProdotto, _indicesProdotto);
        final TableInfo _existingProdotto = TableInfo.read(_db, "prodotto");
        if (! _infoProdotto.equals(_existingProdotto)) {
          return new RoomOpenHelper.ValidationResult(false, "prodotto(com.example.greenmarket.db.model.Prodotto).\n"
                  + " Expected:\n" + _infoProdotto + "\n"
                  + " Found:\n" + _existingProdotto);
        }
        final HashMap<String, TableInfo.Column> _columnsProdottiInRicette = new HashMap<String, TableInfo.Column>(2);
        _columnsProdottiInRicette.put("ricetta", new TableInfo.Column("ricetta", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProdottiInRicette.put("prodotto", new TableInfo.Column("prodotto", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProdottiInRicette = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysProdottiInRicette.add(new TableInfo.ForeignKey("ricetta", "NO ACTION", "NO ACTION",Arrays.asList("ricetta"), Arrays.asList("nome")));
        _foreignKeysProdottiInRicette.add(new TableInfo.ForeignKey("prodotto", "NO ACTION", "NO ACTION",Arrays.asList("prodotto"), Arrays.asList("nome")));
        final HashSet<TableInfo.Index> _indicesProdottiInRicette = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProdottiInRicette = new TableInfo("prodotti_in_ricette", _columnsProdottiInRicette, _foreignKeysProdottiInRicette, _indicesProdottiInRicette);
        final TableInfo _existingProdottiInRicette = TableInfo.read(_db, "prodotti_in_ricette");
        if (! _infoProdottiInRicette.equals(_existingProdottiInRicette)) {
          return new RoomOpenHelper.ValidationResult(false, "prodotti_in_ricette(com.example.greenmarket.db.model.ProdottiInRicette).\n"
                  + " Expected:\n" + _infoProdottiInRicette + "\n"
                  + " Found:\n" + _existingProdottiInRicette);
        }
        final HashMap<String, TableInfo.Column> _columnsComposizioneScontrini = new HashMap<String, TableInfo.Column>(3);
        _columnsComposizioneScontrini.put("scontrino", new TableInfo.Column("scontrino", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComposizioneScontrini.put("prodotto", new TableInfo.Column("prodotto", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComposizioneScontrini.put("quantita", new TableInfo.Column("quantita", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysComposizioneScontrini = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysComposizioneScontrini.add(new TableInfo.ForeignKey("scontrino", "CASCADE", "NO ACTION",Arrays.asList("scontrino"), Arrays.asList("_id")));
        _foreignKeysComposizioneScontrini.add(new TableInfo.ForeignKey("prodotto", "NO ACTION", "NO ACTION",Arrays.asList("prodotto"), Arrays.asList("nome")));
        final HashSet<TableInfo.Index> _indicesComposizioneScontrini = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoComposizioneScontrini = new TableInfo("composizione_scontrini", _columnsComposizioneScontrini, _foreignKeysComposizioneScontrini, _indicesComposizioneScontrini);
        final TableInfo _existingComposizioneScontrini = TableInfo.read(_db, "composizione_scontrini");
        if (! _infoComposizioneScontrini.equals(_existingComposizioneScontrini)) {
          return new RoomOpenHelper.ValidationResult(false, "composizione_scontrini(com.example.greenmarket.db.model.ComposizioneScontrini).\n"
                  + " Expected:\n" + _infoComposizioneScontrini + "\n"
                  + " Found:\n" + _existingComposizioneScontrini);
        }
        final HashMap<String, TableInfo.Column> _columnsCodiceSconto = new HashMap<String, TableInfo.Column>(3);
        _columnsCodiceSconto.put("codice", new TableInfo.Column("codice", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCodiceSconto.put("tessera_a_punti", new TableInfo.Column("tessera_a_punti", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCodiceSconto.put("utilizzato", new TableInfo.Column("utilizzato", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCodiceSconto = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysCodiceSconto.add(new TableInfo.ForeignKey("tessera_a_punti", "CASCADE", "NO ACTION",Arrays.asList("tessera_a_punti"), Arrays.asList("_id")));
        final HashSet<TableInfo.Index> _indicesCodiceSconto = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCodiceSconto = new TableInfo("codice_sconto", _columnsCodiceSconto, _foreignKeysCodiceSconto, _indicesCodiceSconto);
        final TableInfo _existingCodiceSconto = TableInfo.read(_db, "codice_sconto");
        if (! _infoCodiceSconto.equals(_existingCodiceSconto)) {
          return new RoomOpenHelper.ValidationResult(false, "codice_sconto(com.example.greenmarket.db.model.CodiceSconto).\n"
                  + " Expected:\n" + _infoCodiceSconto + "\n"
                  + " Found:\n" + _existingCodiceSconto);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "3392d83a8bbed8031cdcf01b6b7ebc2c", "736d196d69032303763f8a7288b93e4c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "utente","tessera_a_punti","scontrino","ricetta","prodotto","prodotti_in_ricette","composizione_scontrini","codice_sconto");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `utente`");
      _db.execSQL("DELETE FROM `tessera_a_punti`");
      _db.execSQL("DELETE FROM `scontrino`");
      _db.execSQL("DELETE FROM `prodotti_in_ricette`");
      _db.execSQL("DELETE FROM `ricetta`");
      _db.execSQL("DELETE FROM `composizione_scontrini`");
      _db.execSQL("DELETE FROM `prodotto`");
      _db.execSQL("DELETE FROM `codice_sconto`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public UtenteDao UtenteDao() {
    if (_utenteDao != null) {
      return _utenteDao;
    } else {
      synchronized(this) {
        if(_utenteDao == null) {
          _utenteDao = new UtenteDao_Impl(this);
        }
        return _utenteDao;
      }
    }
  }

  @Override
  public TesseraAPuntiDao TesseraAPuntiDao() {
    if (_tesseraAPuntiDao != null) {
      return _tesseraAPuntiDao;
    } else {
      synchronized(this) {
        if(_tesseraAPuntiDao == null) {
          _tesseraAPuntiDao = new TesseraAPuntiDao_Impl(this);
        }
        return _tesseraAPuntiDao;
      }
    }
  }

  @Override
  public ScontrinoDao ScontrinoDao() {
    if (_scontrinoDao != null) {
      return _scontrinoDao;
    } else {
      synchronized(this) {
        if(_scontrinoDao == null) {
          _scontrinoDao = new ScontrinoDao_Impl(this);
        }
        return _scontrinoDao;
      }
    }
  }

  @Override
  public RicettaDao RicettaDao() {
    if (_ricettaDao != null) {
      return _ricettaDao;
    } else {
      synchronized(this) {
        if(_ricettaDao == null) {
          _ricettaDao = new RicettaDao_Impl(this);
        }
        return _ricettaDao;
      }
    }
  }

  @Override
  public ProdottoDao ProdottoDao() {
    if (_prodottoDao != null) {
      return _prodottoDao;
    } else {
      synchronized(this) {
        if(_prodottoDao == null) {
          _prodottoDao = new ProdottoDao_Impl(this);
        }
        return _prodottoDao;
      }
    }
  }

  @Override
  public ProdottiInRicetteDao ProdottiInRicetteDao() {
    if (_prodottiInRicetteDao != null) {
      return _prodottiInRicetteDao;
    } else {
      synchronized(this) {
        if(_prodottiInRicetteDao == null) {
          _prodottiInRicetteDao = new ProdottiInRicetteDao_Impl(this);
        }
        return _prodottiInRicetteDao;
      }
    }
  }

  @Override
  public ComposizioneScontriniDao ComposizioneScontriniDao() {
    if (_composizioneScontriniDao != null) {
      return _composizioneScontriniDao;
    } else {
      synchronized(this) {
        if(_composizioneScontriniDao == null) {
          _composizioneScontriniDao = new ComposizioneScontriniDao_Impl(this);
        }
        return _composizioneScontriniDao;
      }
    }
  }

  @Override
  public CodiceScontoDao CodiceScontoDao() {
    if (_codiceScontoDao != null) {
      return _codiceScontoDao;
    } else {
      synchronized(this) {
        if(_codiceScontoDao == null) {
          _codiceScontoDao = new CodiceScontoDao_Impl(this);
        }
        return _codiceScontoDao;
      }
    }
  }
}
