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
import com.example.greenmarket.db.model.CodiceSconto;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CodiceScontoDao_Impl implements CodiceScontoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CodiceSconto> __insertionAdapterOfCodiceSconto;

  private final EntityDeletionOrUpdateAdapter<CodiceSconto> __deletionAdapterOfCodiceSconto;

  private final EntityDeletionOrUpdateAdapter<CodiceSconto> __updateAdapterOfCodiceSconto;

  public CodiceScontoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCodiceSconto = new EntityInsertionAdapter<CodiceSconto>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `codice_sconto` (`codice`,`tessera_a_punti`,`utilizzato`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CodiceSconto value) {
        if (value.getCodice() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getCodice());
        }
        stmt.bindLong(2, value.getTessera_a_punti());
        final int _tmp;
        _tmp = value.getUtilizzato() ? 1 : 0;
        stmt.bindLong(3, _tmp);
      }
    };
    this.__deletionAdapterOfCodiceSconto = new EntityDeletionOrUpdateAdapter<CodiceSconto>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `codice_sconto` WHERE `codice` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CodiceSconto value) {
        if (value.getCodice() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getCodice());
        }
      }
    };
    this.__updateAdapterOfCodiceSconto = new EntityDeletionOrUpdateAdapter<CodiceSconto>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `codice_sconto` SET `codice` = ?,`tessera_a_punti` = ?,`utilizzato` = ? WHERE `codice` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CodiceSconto value) {
        if (value.getCodice() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getCodice());
        }
        stmt.bindLong(2, value.getTessera_a_punti());
        final int _tmp;
        _tmp = value.getUtilizzato() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        if (value.getCodice() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCodice());
        }
      }
    };
  }

  @Override
  public void insert(final CodiceSconto... codice_sconto) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCodiceSconto.insert(codice_sconto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final CodiceSconto codice_sconto) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCodiceSconto.handle(codice_sconto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final CodiceSconto codice_sconto) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCodiceSconto.handle(codice_sconto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public CodiceSconto[] getAll() {
    final String _sql = "SELECT * FROM codice_sconto";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCodice = CursorUtil.getColumnIndexOrThrow(_cursor, "codice");
      final int _cursorIndexOfTesseraAPunti = CursorUtil.getColumnIndexOrThrow(_cursor, "tessera_a_punti");
      final int _cursorIndexOfUtilizzato = CursorUtil.getColumnIndexOrThrow(_cursor, "utilizzato");
      final CodiceSconto[] _result = new CodiceSconto[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final CodiceSconto _item;
        final String _tmpCodice;
        _tmpCodice = _cursor.getString(_cursorIndexOfCodice);
        final int _tmpTessera_a_punti;
        _tmpTessera_a_punti = _cursor.getInt(_cursorIndexOfTesseraAPunti);
        final boolean _tmpUtilizzato;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfUtilizzato);
        _tmpUtilizzato = _tmp != 0;
        _item = new CodiceSconto(_tmpCodice,_tmpTessera_a_punti,_tmpUtilizzato);
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
  public LiveData<CodiceSconto> getCodiceScontoByCodice(final String codice) {
    final String _sql = "SELECT * FROM codice_sconto WHERE codice = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (codice == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, codice);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"codice_sconto"}, false, new Callable<CodiceSconto>() {
      @Override
      public CodiceSconto call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCodice = CursorUtil.getColumnIndexOrThrow(_cursor, "codice");
          final int _cursorIndexOfTesseraAPunti = CursorUtil.getColumnIndexOrThrow(_cursor, "tessera_a_punti");
          final int _cursorIndexOfUtilizzato = CursorUtil.getColumnIndexOrThrow(_cursor, "utilizzato");
          final CodiceSconto _result;
          if(_cursor.moveToFirst()) {
            final String _tmpCodice;
            _tmpCodice = _cursor.getString(_cursorIndexOfCodice);
            final int _tmpTessera_a_punti;
            _tmpTessera_a_punti = _cursor.getInt(_cursorIndexOfTesseraAPunti);
            final boolean _tmpUtilizzato;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfUtilizzato);
            _tmpUtilizzato = _tmp != 0;
            _result = new CodiceSconto(_tmpCodice,_tmpTessera_a_punti,_tmpUtilizzato);
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
