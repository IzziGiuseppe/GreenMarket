package com.example.greenmarket.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.greenmarket.db.model.Ricetta;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RicettaDao_Impl implements RicettaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Ricetta> __insertionAdapterOfRicetta;

  private final EntityDeletionOrUpdateAdapter<Ricetta> __deletionAdapterOfRicetta;

  private final EntityDeletionOrUpdateAdapter<Ricetta> __updateAdapterOfRicetta;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllRicette;

  public RicettaDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRicetta = new EntityInsertionAdapter<Ricetta>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `ricetta` (`nome`,`descrizione`,`foto`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Ricetta value) {
        if (value.getNome() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNome());
        }
        if (value.getDescrizione() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDescrizione());
        }
        if (value.getFoto() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFoto());
        }
      }
    };
    this.__deletionAdapterOfRicetta = new EntityDeletionOrUpdateAdapter<Ricetta>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `ricetta` WHERE `nome` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Ricetta value) {
        if (value.getNome() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNome());
        }
      }
    };
    this.__updateAdapterOfRicetta = new EntityDeletionOrUpdateAdapter<Ricetta>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `ricetta` SET `nome` = ?,`descrizione` = ?,`foto` = ? WHERE `nome` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Ricetta value) {
        if (value.getNome() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNome());
        }
        if (value.getDescrizione() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDescrizione());
        }
        if (value.getFoto() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFoto());
        }
        if (value.getNome() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNome());
        }
      }
    };
    this.__preparedStmtOfDeleteAllRicette = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM ricetta";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Ricetta... ricetta) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRicetta.insert(ricetta);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Ricetta ricetta) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfRicetta.handle(ricetta);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Ricetta ricetta) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfRicetta.handle(ricetta);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllRicette() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllRicette.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllRicette.release(_stmt);
    }
  }

  @Override
  public Ricetta[] getAll() {
    final String _sql = "SELECT * FROM ricetta";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
      final int _cursorIndexOfDescrizione = CursorUtil.getColumnIndexOrThrow(_cursor, "descrizione");
      final int _cursorIndexOfFoto = CursorUtil.getColumnIndexOrThrow(_cursor, "foto");
      final Ricetta[] _result = new Ricetta[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final Ricetta _item;
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpDescrizione;
        _tmpDescrizione = _cursor.getString(_cursorIndexOfDescrizione);
        final String _tmpFoto;
        _tmpFoto = _cursor.getString(_cursorIndexOfFoto);
        _item = new Ricetta(_tmpNome,_tmpDescrizione,_tmpFoto);
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
  public Ricetta getRicettaByNome(final String nome) {
    final String _sql = "SELECT * FROM ricetta WHERE nome = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (nome == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nome);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
      final int _cursorIndexOfDescrizione = CursorUtil.getColumnIndexOrThrow(_cursor, "descrizione");
      final int _cursorIndexOfFoto = CursorUtil.getColumnIndexOrThrow(_cursor, "foto");
      final Ricetta _result;
      if(_cursor.moveToFirst()) {
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpDescrizione;
        _tmpDescrizione = _cursor.getString(_cursorIndexOfDescrizione);
        final String _tmpFoto;
        _tmpFoto = _cursor.getString(_cursorIndexOfFoto);
        _result = new Ricetta(_tmpNome,_tmpDescrizione,_tmpFoto);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
