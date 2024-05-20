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
import com.example.greenmarket.db.model.Prodotto;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ProdottoDao_Impl implements ProdottoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Prodotto> __insertionAdapterOfProdotto;

  private final EntityDeletionOrUpdateAdapter<Prodotto> __deletionAdapterOfProdotto;

  private final EntityDeletionOrUpdateAdapter<Prodotto> __updateAdapterOfProdotto;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllProdotti;

  public ProdottoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProdotto = new EntityInsertionAdapter<Prodotto>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `prodotto` (`nome`,`descrizione`,`prezzo`,`foto`,`unita_di_misura`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Prodotto value) {
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
        stmt.bindDouble(3, value.getPrezzo());
        if (value.getFoto() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFoto());
        }
        if (value.getUnita_di_misura() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getUnita_di_misura());
        }
      }
    };
    this.__deletionAdapterOfProdotto = new EntityDeletionOrUpdateAdapter<Prodotto>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `prodotto` WHERE `nome` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Prodotto value) {
        if (value.getNome() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNome());
        }
      }
    };
    this.__updateAdapterOfProdotto = new EntityDeletionOrUpdateAdapter<Prodotto>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `prodotto` SET `nome` = ?,`descrizione` = ?,`prezzo` = ?,`foto` = ?,`unita_di_misura` = ? WHERE `nome` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Prodotto value) {
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
        stmt.bindDouble(3, value.getPrezzo());
        if (value.getFoto() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFoto());
        }
        if (value.getUnita_di_misura() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getUnita_di_misura());
        }
        if (value.getNome() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getNome());
        }
      }
    };
    this.__preparedStmtOfDeleteAllProdotti = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM prodotto";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Prodotto... prodotto) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfProdotto.insert(prodotto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Prodotto prodotto) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfProdotto.handle(prodotto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Prodotto prodotto) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfProdotto.handle(prodotto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllProdotti() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllProdotti.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllProdotti.release(_stmt);
    }
  }

  @Override
  public Prodotto[] getAll() {
    final String _sql = "SELECT * FROM prodotto";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
      final int _cursorIndexOfDescrizione = CursorUtil.getColumnIndexOrThrow(_cursor, "descrizione");
      final int _cursorIndexOfPrezzo = CursorUtil.getColumnIndexOrThrow(_cursor, "prezzo");
      final int _cursorIndexOfFoto = CursorUtil.getColumnIndexOrThrow(_cursor, "foto");
      final int _cursorIndexOfUnitaDiMisura = CursorUtil.getColumnIndexOrThrow(_cursor, "unita_di_misura");
      final Prodotto[] _result = new Prodotto[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final Prodotto _item;
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpDescrizione;
        _tmpDescrizione = _cursor.getString(_cursorIndexOfDescrizione);
        final float _tmpPrezzo;
        _tmpPrezzo = _cursor.getFloat(_cursorIndexOfPrezzo);
        final String _tmpFoto;
        _tmpFoto = _cursor.getString(_cursorIndexOfFoto);
        final String _tmpUnita_di_misura;
        _tmpUnita_di_misura = _cursor.getString(_cursorIndexOfUnitaDiMisura);
        _item = new Prodotto(_tmpNome,_tmpDescrizione,_tmpPrezzo,_tmpFoto,_tmpUnita_di_misura);
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
  public Prodotto getProdottoByNome(final String nome) {
    final String _sql = "SELECT * FROM prodotto WHERE nome = ?";
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
      final int _cursorIndexOfPrezzo = CursorUtil.getColumnIndexOrThrow(_cursor, "prezzo");
      final int _cursorIndexOfFoto = CursorUtil.getColumnIndexOrThrow(_cursor, "foto");
      final int _cursorIndexOfUnitaDiMisura = CursorUtil.getColumnIndexOrThrow(_cursor, "unita_di_misura");
      final Prodotto _result;
      if(_cursor.moveToFirst()) {
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpDescrizione;
        _tmpDescrizione = _cursor.getString(_cursorIndexOfDescrizione);
        final float _tmpPrezzo;
        _tmpPrezzo = _cursor.getFloat(_cursorIndexOfPrezzo);
        final String _tmpFoto;
        _tmpFoto = _cursor.getString(_cursorIndexOfFoto);
        final String _tmpUnita_di_misura;
        _tmpUnita_di_misura = _cursor.getString(_cursorIndexOfUnitaDiMisura);
        _result = new Prodotto(_tmpNome,_tmpDescrizione,_tmpPrezzo,_tmpFoto,_tmpUnita_di_misura);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getCount() {
    final String _sql = "SELECT COUNT(*) FROM prodotto";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
