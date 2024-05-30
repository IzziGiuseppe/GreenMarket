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
import com.example.greenmarket.db.model.Scontrino;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ScontrinoDao_Impl implements ScontrinoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Scontrino> __insertionAdapterOfScontrino;

  private final EntityDeletionOrUpdateAdapter<Scontrino> __deletionAdapterOfScontrino;

  private final EntityDeletionOrUpdateAdapter<Scontrino> __updateAdapterOfScontrino;

  public ScontrinoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfScontrino = new EntityInsertionAdapter<Scontrino>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `scontrino` (`_id`,`data`,`utente`,`codice_sconto`,`valido`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Scontrino value) {
        stmt.bindLong(1, value.get_id());
        if (value.getData() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getData());
        }
        if (value.getUtente() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUtente());
        }
        if (value.getCodice_sconto() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCodice_sconto());
        }
        final int _tmp;
        _tmp = value.getValido() ? 1 : 0;
        stmt.bindLong(5, _tmp);
      }
    };
    this.__deletionAdapterOfScontrino = new EntityDeletionOrUpdateAdapter<Scontrino>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `scontrino` WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Scontrino value) {
        stmt.bindLong(1, value.get_id());
      }
    };
    this.__updateAdapterOfScontrino = new EntityDeletionOrUpdateAdapter<Scontrino>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `scontrino` SET `_id` = ?,`data` = ?,`utente` = ?,`codice_sconto` = ?,`valido` = ? WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Scontrino value) {
        stmt.bindLong(1, value.get_id());
        if (value.getData() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getData());
        }
        if (value.getUtente() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUtente());
        }
        if (value.getCodice_sconto() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCodice_sconto());
        }
        final int _tmp;
        _tmp = value.getValido() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        stmt.bindLong(6, value.get_id());
      }
    };
  }

  @Override
  public void insert(final Scontrino... scontrino) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfScontrino.insert(scontrino);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Scontrino scontrino) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfScontrino.handle(scontrino);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Scontrino scontrino) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfScontrino.handle(scontrino);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Scontrino[] getAll() {
    final String _sql = "SELECT * FROM scontrino";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
      final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
      final int _cursorIndexOfUtente = CursorUtil.getColumnIndexOrThrow(_cursor, "utente");
      final int _cursorIndexOfCodiceSconto = CursorUtil.getColumnIndexOrThrow(_cursor, "codice_sconto");
      final int _cursorIndexOfValido = CursorUtil.getColumnIndexOrThrow(_cursor, "valido");
      final Scontrino[] _result = new Scontrino[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final Scontrino _item;
        final int _tmp_id;
        _tmp_id = _cursor.getInt(_cursorIndexOfId);
        final String _tmpData;
        _tmpData = _cursor.getString(_cursorIndexOfData);
        final String _tmpUtente;
        _tmpUtente = _cursor.getString(_cursorIndexOfUtente);
        final String _tmpCodice_sconto;
        _tmpCodice_sconto = _cursor.getString(_cursorIndexOfCodiceSconto);
        final boolean _tmpValido;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfValido);
        _tmpValido = _tmp != 0;
        _item = new Scontrino(_tmp_id,_tmpData,_tmpUtente,_tmpCodice_sconto,_tmpValido);
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
  public LiveData<Scontrino> getScontrinoById(final int id) {
    final String _sql = "SELECT * FROM scontrino WHERE _id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return __db.getInvalidationTracker().createLiveData(new String[]{"scontrino"}, false, new Callable<Scontrino>() {
      @Override
      public Scontrino call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfUtente = CursorUtil.getColumnIndexOrThrow(_cursor, "utente");
          final int _cursorIndexOfCodiceSconto = CursorUtil.getColumnIndexOrThrow(_cursor, "codice_sconto");
          final int _cursorIndexOfValido = CursorUtil.getColumnIndexOrThrow(_cursor, "valido");
          final Scontrino _result;
          if(_cursor.moveToFirst()) {
            final int _tmp_id;
            _tmp_id = _cursor.getInt(_cursorIndexOfId);
            final String _tmpData;
            _tmpData = _cursor.getString(_cursorIndexOfData);
            final String _tmpUtente;
            _tmpUtente = _cursor.getString(_cursorIndexOfUtente);
            final String _tmpCodice_sconto;
            _tmpCodice_sconto = _cursor.getString(_cursorIndexOfCodiceSconto);
            final boolean _tmpValido;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfValido);
            _tmpValido = _tmp != 0;
            _result = new Scontrino(_tmp_id,_tmpData,_tmpUtente,_tmpCodice_sconto,_tmpValido);
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
