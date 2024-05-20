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
import com.example.greenmarket.db.model.ComposizioneScontrini;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ComposizioneScontriniDao_Impl implements ComposizioneScontriniDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ComposizioneScontrini> __insertionAdapterOfComposizioneScontrini;

  private final EntityDeletionOrUpdateAdapter<ComposizioneScontrini> __deletionAdapterOfComposizioneScontrini;

  private final EntityDeletionOrUpdateAdapter<ComposizioneScontrini> __updateAdapterOfComposizioneScontrini;

  public ComposizioneScontriniDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfComposizioneScontrini = new EntityInsertionAdapter<ComposizioneScontrini>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `composizione_scontrini` (`scontrino`,`prodotto`,`quantita`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ComposizioneScontrini value) {
        stmt.bindLong(1, value.getScontrino());
        if (value.getProdotto() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getProdotto());
        }
        stmt.bindDouble(3, value.getQuantita());
      }
    };
    this.__deletionAdapterOfComposizioneScontrini = new EntityDeletionOrUpdateAdapter<ComposizioneScontrini>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `composizione_scontrini` WHERE `scontrino` = ? AND `prodotto` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ComposizioneScontrini value) {
        stmt.bindLong(1, value.getScontrino());
        if (value.getProdotto() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getProdotto());
        }
      }
    };
    this.__updateAdapterOfComposizioneScontrini = new EntityDeletionOrUpdateAdapter<ComposizioneScontrini>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `composizione_scontrini` SET `scontrino` = ?,`prodotto` = ?,`quantita` = ? WHERE `scontrino` = ? AND `prodotto` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ComposizioneScontrini value) {
        stmt.bindLong(1, value.getScontrino());
        if (value.getProdotto() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getProdotto());
        }
        stmt.bindDouble(3, value.getQuantita());
        stmt.bindLong(4, value.getScontrino());
        if (value.getProdotto() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getProdotto());
        }
      }
    };
  }

  @Override
  public void insert(final ComposizioneScontrini... composizione_scontrini) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfComposizioneScontrini.insert(composizione_scontrini);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final ComposizioneScontrini composizione_scontrini) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfComposizioneScontrini.handle(composizione_scontrini);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ComposizioneScontrini composizione_scontrini) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfComposizioneScontrini.handle(composizione_scontrini);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public ComposizioneScontrini[] getAll() {
    final String _sql = "SELECT * FROM composizione_scontrini";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfScontrino = CursorUtil.getColumnIndexOrThrow(_cursor, "scontrino");
      final int _cursorIndexOfProdotto = CursorUtil.getColumnIndexOrThrow(_cursor, "prodotto");
      final int _cursorIndexOfQuantita = CursorUtil.getColumnIndexOrThrow(_cursor, "quantita");
      final ComposizioneScontrini[] _result = new ComposizioneScontrini[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final ComposizioneScontrini _item;
        final int _tmpScontrino;
        _tmpScontrino = _cursor.getInt(_cursorIndexOfScontrino);
        final String _tmpProdotto;
        _tmpProdotto = _cursor.getString(_cursorIndexOfProdotto);
        final float _tmpQuantita;
        _tmpQuantita = _cursor.getFloat(_cursorIndexOfQuantita);
        _item = new ComposizioneScontrini(_tmpScontrino,_tmpProdotto,_tmpQuantita);
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
  public LiveData<ComposizioneScontrini> getComposizioneScontrinoByScontrino(final int scontrino) {
    final String _sql = "SELECT * FROM composizione_scontrini WHERE scontrino = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, scontrino);
    return __db.getInvalidationTracker().createLiveData(new String[]{"composizione_scontrini"}, false, new Callable<ComposizioneScontrini>() {
      @Override
      public ComposizioneScontrini call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfScontrino = CursorUtil.getColumnIndexOrThrow(_cursor, "scontrino");
          final int _cursorIndexOfProdotto = CursorUtil.getColumnIndexOrThrow(_cursor, "prodotto");
          final int _cursorIndexOfQuantita = CursorUtil.getColumnIndexOrThrow(_cursor, "quantita");
          final ComposizioneScontrini _result;
          if(_cursor.moveToFirst()) {
            final int _tmpScontrino;
            _tmpScontrino = _cursor.getInt(_cursorIndexOfScontrino);
            final String _tmpProdotto;
            _tmpProdotto = _cursor.getString(_cursorIndexOfProdotto);
            final float _tmpQuantita;
            _tmpQuantita = _cursor.getFloat(_cursorIndexOfQuantita);
            _result = new ComposizioneScontrini(_tmpScontrino,_tmpProdotto,_tmpQuantita);
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
}
