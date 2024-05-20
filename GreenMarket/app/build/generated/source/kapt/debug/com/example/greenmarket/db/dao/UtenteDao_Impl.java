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
import com.example.greenmarket.db.model.Utente;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UtenteDao_Impl implements UtenteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Utente> __insertionAdapterOfUtente;

  private final EntityDeletionOrUpdateAdapter<Utente> __deletionAdapterOfUtente;

  private final EntityDeletionOrUpdateAdapter<Utente> __updateAdapterOfUtente;

  public UtenteDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUtente = new EntityInsertionAdapter<Utente>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `utente` (`email`,`password`,`nome`,`cognome`,`indirizzo`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Utente value) {
        if (value.getEmail() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getEmail());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPassword());
        }
        if (value.getNome() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getNome());
        }
        if (value.getCognome() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCognome());
        }
        if (value.getIndirizzo() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getIndirizzo());
        }
      }
    };
    this.__deletionAdapterOfUtente = new EntityDeletionOrUpdateAdapter<Utente>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `utente` WHERE `email` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Utente value) {
        if (value.getEmail() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getEmail());
        }
      }
    };
    this.__updateAdapterOfUtente = new EntityDeletionOrUpdateAdapter<Utente>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `utente` SET `email` = ?,`password` = ?,`nome` = ?,`cognome` = ?,`indirizzo` = ? WHERE `email` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Utente value) {
        if (value.getEmail() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getEmail());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPassword());
        }
        if (value.getNome() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getNome());
        }
        if (value.getCognome() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCognome());
        }
        if (value.getIndirizzo() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getIndirizzo());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEmail());
        }
      }
    };
  }

  @Override
  public void insert(final Utente... utente) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUtente.insert(utente);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Utente utente) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfUtente.handle(utente);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Utente utente) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUtente.handle(utente);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Utente[] getAll() {
    final String _sql = "SELECT * FROM utente";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
      final int _cursorIndexOfCognome = CursorUtil.getColumnIndexOrThrow(_cursor, "cognome");
      final int _cursorIndexOfIndirizzo = CursorUtil.getColumnIndexOrThrow(_cursor, "indirizzo");
      final Utente[] _result = new Utente[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final Utente _item;
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final String _tmpPassword;
        _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpCognome;
        _tmpCognome = _cursor.getString(_cursorIndexOfCognome);
        final String _tmpIndirizzo;
        _tmpIndirizzo = _cursor.getString(_cursorIndexOfIndirizzo);
        _item = new Utente(_tmpEmail,_tmpPassword,_tmpNome,_tmpCognome,_tmpIndirizzo);
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
  public LiveData<Utente> getUtenteByEmail(final String email) {
    final String _sql = "SELECT * FROM utente WHERE email = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (email == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, email);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"utente"}, false, new Callable<Utente>() {
      @Override
      public Utente call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfCognome = CursorUtil.getColumnIndexOrThrow(_cursor, "cognome");
          final int _cursorIndexOfIndirizzo = CursorUtil.getColumnIndexOrThrow(_cursor, "indirizzo");
          final Utente _result;
          if(_cursor.moveToFirst()) {
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPassword;
            _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final String _tmpCognome;
            _tmpCognome = _cursor.getString(_cursorIndexOfCognome);
            final String _tmpIndirizzo;
            _tmpIndirizzo = _cursor.getString(_cursorIndexOfIndirizzo);
            _result = new Utente(_tmpEmail,_tmpPassword,_tmpNome,_tmpCognome,_tmpIndirizzo);
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
