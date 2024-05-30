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
import com.example.greenmarket.db.model.TesseraAPunti;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TesseraAPuntiDao_Impl implements TesseraAPuntiDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TesseraAPunti> __insertionAdapterOfTesseraAPunti;

  private final EntityDeletionOrUpdateAdapter<TesseraAPunti> __deletionAdapterOfTesseraAPunti;

  private final EntityDeletionOrUpdateAdapter<TesseraAPunti> __updateAdapterOfTesseraAPunti;

  public TesseraAPuntiDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTesseraAPunti = new EntityInsertionAdapter<TesseraAPunti>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `tessera_a_punti` (`_id`,`saldo`,`punti`,`utente`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TesseraAPunti value) {
        stmt.bindLong(1, value.get_id());
        stmt.bindDouble(2, value.getSaldo());
        stmt.bindLong(3, value.getPunti());
        if (value.getUtente() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUtente());
        }
      }
    };
    this.__deletionAdapterOfTesseraAPunti = new EntityDeletionOrUpdateAdapter<TesseraAPunti>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `tessera_a_punti` WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TesseraAPunti value) {
        stmt.bindLong(1, value.get_id());
      }
    };
    this.__updateAdapterOfTesseraAPunti = new EntityDeletionOrUpdateAdapter<TesseraAPunti>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `tessera_a_punti` SET `_id` = ?,`saldo` = ?,`punti` = ?,`utente` = ? WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TesseraAPunti value) {
        stmt.bindLong(1, value.get_id());
        stmt.bindDouble(2, value.getSaldo());
        stmt.bindLong(3, value.getPunti());
        if (value.getUtente() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUtente());
        }
        stmt.bindLong(5, value.get_id());
      }
    };
  }

  @Override
  public void insert(final TesseraAPunti... tesseraAPunti) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTesseraAPunti.insert(tesseraAPunti);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final TesseraAPunti tesseraAPunti) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTesseraAPunti.handle(tesseraAPunti);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final TesseraAPunti tesseraAPunti) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTesseraAPunti.handle(tesseraAPunti);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public TesseraAPunti[] getAll() {
    final String _sql = "SELECT * FROM tessera_a_punti";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
      final int _cursorIndexOfSaldo = CursorUtil.getColumnIndexOrThrow(_cursor, "saldo");
      final int _cursorIndexOfPunti = CursorUtil.getColumnIndexOrThrow(_cursor, "punti");
      final int _cursorIndexOfUtente = CursorUtil.getColumnIndexOrThrow(_cursor, "utente");
      final TesseraAPunti[] _result = new TesseraAPunti[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final TesseraAPunti _item;
        final int _tmp_id;
        _tmp_id = _cursor.getInt(_cursorIndexOfId);
        final float _tmpSaldo;
        _tmpSaldo = _cursor.getFloat(_cursorIndexOfSaldo);
        final int _tmpPunti;
        _tmpPunti = _cursor.getInt(_cursorIndexOfPunti);
        final String _tmpUtente;
        _tmpUtente = _cursor.getString(_cursorIndexOfUtente);
        _item = new TesseraAPunti(_tmp_id,_tmpSaldo,_tmpPunti,_tmpUtente);
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
  public LiveData<TesseraAPunti> getTesseraAPuntiById(final int id) {
    final String _sql = "SELECT * FROM tessera_a_punti WHERE _id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return __db.getInvalidationTracker().createLiveData(new String[]{"tessera_a_punti"}, false, new Callable<TesseraAPunti>() {
      @Override
      public TesseraAPunti call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfSaldo = CursorUtil.getColumnIndexOrThrow(_cursor, "saldo");
          final int _cursorIndexOfPunti = CursorUtil.getColumnIndexOrThrow(_cursor, "punti");
          final int _cursorIndexOfUtente = CursorUtil.getColumnIndexOrThrow(_cursor, "utente");
          final TesseraAPunti _result;
          if(_cursor.moveToFirst()) {
            final int _tmp_id;
            _tmp_id = _cursor.getInt(_cursorIndexOfId);
            final float _tmpSaldo;
            _tmpSaldo = _cursor.getFloat(_cursorIndexOfSaldo);
            final int _tmpPunti;
            _tmpPunti = _cursor.getInt(_cursorIndexOfPunti);
            final String _tmpUtente;
            _tmpUtente = _cursor.getString(_cursorIndexOfUtente);
            _result = new TesseraAPunti(_tmp_id,_tmpSaldo,_tmpPunti,_tmpUtente);
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
