package com.example.greenmarket.db.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\b\u0010\u0006\u001a\u00020\u0003H\'J\u0013\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\'\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\'J!\u0010\r\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\b\"\u00020\u0005H\'\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0010"}, d2 = {"Lcom/example/greenmarket/db/dao/RicettaDao;", "", "delete", "", "ricetta", "Lcom/example/greenmarket/db/model/Ricetta;", "deleteAllRicette", "getAll", "", "()[Lcom/example/greenmarket/db/model/Ricetta;", "getRicettaByNome", "nome", "", "insert", "([Lcom/example/greenmarket/db/model/Ricetta;)V", "update", "app_debug"})
@androidx.room.Dao
public abstract interface RicettaDao {
    
    @androidx.room.Query(value = "SELECT * FROM ricetta")
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.model.Ricetta[] getAll();
    
    @androidx.room.Query(value = "SELECT * FROM ricetta WHERE nome = :nome")
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.model.Ricetta getRicettaByNome(@org.jetbrains.annotations.NotNull
    java.lang.String nome);
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    public abstract void insert(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.db.model.Ricetta... ricetta);
    
    @androidx.room.Update
    public abstract void update(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.db.model.Ricetta ricetta);
    
    @androidx.room.Delete
    public abstract void delete(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.db.model.Ricetta ricetta);
    
    @androidx.room.Query(value = "DELETE FROM ricetta")
    public abstract void deleteAllRicette();
}