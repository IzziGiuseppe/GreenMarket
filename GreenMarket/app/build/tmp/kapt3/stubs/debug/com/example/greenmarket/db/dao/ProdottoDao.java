package com.example.greenmarket.db.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\b\u0010\u0006\u001a\u00020\u0003H\'J\u0013\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\'\u00a2\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\'J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\'J!\u0010\u000f\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\b\"\u00020\u0005H\'\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0012"}, d2 = {"Lcom/example/greenmarket/db/dao/ProdottoDao;", "", "delete", "", "prodotto", "Lcom/example/greenmarket/db/model/Prodotto;", "deleteAllProdotti", "getAll", "", "()[Lcom/example/greenmarket/db/model/Prodotto;", "getCount", "", "getProdottoByNome", "nome", "", "insert", "([Lcom/example/greenmarket/db/model/Prodotto;)V", "update", "app_debug"})
@androidx.room.Dao
public abstract interface ProdottoDao {
    
    @androidx.room.Query(value = "SELECT * FROM prodotto")
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.model.Prodotto[] getAll();
    
    @androidx.room.Query(value = "SELECT * FROM prodotto WHERE nome = :nome")
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.model.Prodotto getProdottoByNome(@org.jetbrains.annotations.NotNull
    java.lang.String nome);
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    public abstract void insert(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.db.model.Prodotto... prodotto);
    
    @androidx.room.Update
    public abstract void update(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.db.model.Prodotto prodotto);
    
    @androidx.room.Delete
    public abstract void delete(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.db.model.Prodotto prodotto);
    
    @androidx.room.Query(value = "DELETE FROM prodotto")
    public abstract void deleteAllProdotti();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM prodotto")
    public abstract int getCount();
}