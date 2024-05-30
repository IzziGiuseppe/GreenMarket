package com.example.greenmarket.db.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\'\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u000b\u001a\u00020\fH\'J!\u0010\r\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0007\"\u00020\u0005H\'\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0010"}, d2 = {"Lcom/example/greenmarket/db/dao/TesseraAPuntiDao;", "", "delete", "", "tesseraAPunti", "Lcom/example/greenmarket/db/model/TesseraAPunti;", "getAll", "", "()[Lcom/example/greenmarket/db/model/TesseraAPunti;", "getTesseraAPuntiById", "Landroidx/lifecycle/LiveData;", "id", "", "insert", "([Lcom/example/greenmarket/db/model/TesseraAPunti;)V", "update", "app_debug"})
@androidx.room.Dao
public abstract interface TesseraAPuntiDao {
    
    @androidx.room.Query(value = "SELECT * FROM tessera_a_punti")
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.model.TesseraAPunti[] getAll();
    
    @androidx.room.Query(value = "SELECT * FROM tessera_a_punti WHERE _id = :id")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<com.example.greenmarket.db.model.TesseraAPunti> getTesseraAPuntiById(int id);
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.ABORT)
    public abstract void insert(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.db.model.TesseraAPunti... tesseraAPunti);
    
    @androidx.room.Update
    public abstract void update(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.db.model.TesseraAPunti tesseraAPunti);
    
    @androidx.room.Delete
    public abstract void delete(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.db.model.TesseraAPunti tesseraAPunti);
}