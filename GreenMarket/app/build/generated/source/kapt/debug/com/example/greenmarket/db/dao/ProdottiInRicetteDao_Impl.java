package com.example.greenmarket.db.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.greenmarket.db.model.ProdottiInRicette;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ProdottiInRicetteDao_Impl implements ProdottiInRicetteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProdottiInRicette> __insertionAdapterOfProdottiInRicette;

  private final EntityDeletionOrUpdateAdapter<ProdottiInRicette> __deletionAdapterOfProdottiInRicette;

  private final EntityDeletionOrUpdateAdapter<ProdottiInRicette> __updateAdapterOfProdottiInRicette;

  public ProdottiInRicetteDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProdottiInRicette = new EntityInsertionAdapter<ProdottiInRicette>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `prodotti_in_ricette` (`ricetta`,`prodotto`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ProdottiInRicette value) {
        if (value.getRicetta() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getRicetta());
        }
        if (value.getProdotto() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getProdotto());
        }
      }
    };
    this.__deletionAdapterOfProdottiInRicette = new EntityDeletionOrUpdateAdapter<ProdottiInRicette>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `prodotti_in_ricette` WHERE `ricetta` = ? AND `prodotto` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ProdottiInRicette value) {
        if (value.getRicetta() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getRicetta());
        }
        if (value.getProdotto() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getProdotto());
        }
      }
    };
    this.__updateAdapterOfProdottiInRicette = new EntityDeletionOrUpdateAdapter<ProdottiInRicette>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `prodotti_in_ricette` SET `ricetta` = ?,`prodotto` = ? WHERE `ricetta` = ? AND `prodotto` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ProdottiInRicette value) {
        if (value.getRicetta() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getRicetta());
        }
        if (value.getProdotto() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getProdotto());
        }
        if (value.getRicetta() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getRicetta());
        }
        if (value.getProdotto() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getProdotto());
        }
      }
    };
  }

  @Override
  public void insert(final ProdottiInRicette... prodotti_in_ricette) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfProdottiInRicette.insert(prodotti_in_ricette);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final ProdottiInRicette prodotti_in_ricette) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfProdottiInRicette.handle(prodotti_in_ricette);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ProdottiInRicette prodotti_in_ricette) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfProdottiInRicette.handle(prodotti_in_ricette);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public ProdottiInRicette[] getAll() {
    final String _sql = "SELECT * FROM prodotti_in_ricette";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfRicetta = CursorUtil.getColumnIndexOrThrow(_cursor, "ricetta");
      final int _cursorIndexOfProdotto = CursorUtil.getColumnIndexOrThrow(_cursor, "prodotto");
      final ProdottiInRicette[] _result = new ProdottiInRicette[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final ProdottiInRicette _item;
        final String _tmpRicetta;
        _tmpRicetta = _cursor.getString(_cursorIndexOfRicetta);
        final String _tmpProdotto;
        _tmpProdotto = _cursor.getString(_cursorIndexOfProdotto);
        _item = new ProdottiInRicette(_tmpRicetta,_tmpProdotto);
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<ProdottiInRicette> getProdottiInRicetteByRicetta(final String ricetta) {
    final String _sql = "SELECT * FROM prodotti_in_ricette WHERE ricetta = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (ricetta == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, ricetta);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"prodotti_in_ricette"}, false, new Callable<ProdottiInRicette>() {
      @Override
      public ProdottiInRicette call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRicetta = CursorUtil.getColumnIndexOrThrow(_cursor, "ricetta");
          final int _cursorIndexOfProdotto = CursorUtil.getColumnIndexOrThrow(_cursor, "prodotto");
          final ProdottiInRicette _result;
          if(_cursor.moveToFirst()) {
            final String _tmpRicetta;
            _tmpRicetta = _cursor.getString(_cursorIndexOfRicetta);
            final String _tmpProdotto;
            _tmpProdotto = _cursor.getString(_cursorIndexOfProdotto);
            _result = new ProdottiInRicette(_tmpRicetta,_tmpProdotto);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public ProdottiInRicette[] getProdottiInRicetteByProdotto(final String prodotto) {
    final String _sql = "SELECT * FROM prodotti_in_ricette WHERE prodotto = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (prodotto == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, prodotto);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfRicetta = CursorUtil.getColumnIndexOrThrow(_cursor, "ricetta");
      final int _cursorIndexOfProdotto = CursorUtil.getColumnIndexOrThrow(_cursor, "prodotto");
      final ProdottiInRicette[] _result = new ProdottiInRicette[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final ProdottiInRicette _item;
        final String _tmpRicetta;
        _tmpRicetta = _cursor.getString(_cursorIndexOfRicetta);
        final String _tmpProdotto;
        _tmpProdotto = _cursor.getString(_cursorIndexOfProdotto);
        _item = new ProdottiInRicette(_tmpRicetta,_tmpProdotto);
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
