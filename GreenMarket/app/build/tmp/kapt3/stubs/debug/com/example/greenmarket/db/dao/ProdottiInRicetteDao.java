package com.example.greenmarket.db.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\b\u0010\u0006\u001a\u00020\u0003H\'J\u0013\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\'\u00a2\u0006\u0002\u0010\tJ\u001b\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\u000b\u001a\u00020\fH\'\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\u0010\u001a\u00020\fH\'J!\u0010\u0011\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\b\"\u00020\u0005H\'\u00a2\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0014"}, d2 = {"Lcom/example/greenmarket/db/dao/ProdottiInRicetteDao;", "", "delete", "", "prodotti_in_ricette", "Lcom/example/greenmarket/db/model/ProdottiInRicette;", "deleteAllProdottiInRicette", "getAll", "", "()[Lcom/example/greenmarket/db/model/ProdottiInRicette;", "getProdottiInRicetteByProdotto", "prodotto", "", "(Ljava/lang/String;)[Lcom/example/greenmarket/db/model/ProdottiInRicette;", "getProdottiInRicetteByRicetta", "Landroidx/lifecycle/LiveData;", "ricetta", "insert", "([Lcom/example/greenmarket/db/model/ProdottiInRicette;)V", "update", "app_debug"})
@androidx.room.Dao
public abstract interface ProdottiInRicetteDao {
    
    @androidx.room.Query(value = "SELECT * FROM prodotti_in_ricette WHERE (Ricetta, Prodotto) IN (SELECT Ricetta, MIN(Prodotto) FROM prodotti_in_ricette GROUP BY Ricetta);")
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.model.ProdottiInRicette[] getAll();
    
    @androidx.room.Query(value = "SELECT * FROM prodotti_in_ricette WHERE ricetta = :ricetta")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<com.example.greenmarket.db.model.ProdottiInRicette> getProdottiInRicetteByRicetta(@org.jetbrains.annotations.NotNull
    java.lang.String ricetta);
    
    @androidx.room.Query(value = "SELECT * FROM prodotti_in_ricette WHERE prodotto = :prodotto")
    @org.jetbrains.annotations.NotNull
    public abstract com.example.greenmarket.db.model.ProdottiInRicette[] getProdottiInRicetteByProdotto(@org.jetbrains.annotations.NotNull
    java.lang.String prodotto);
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    public abstract void insert(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.db.model.ProdottiInRicette... prodotti_in_ricette);
    
    @androidx.room.Update
    public abstract void update(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.db.model.ProdottiInRicette prodotti_in_ricette);
    
    @androidx.room.Delete
    public abstract void delete(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.db.model.ProdottiInRicette prodotti_in_ricette);
    
    @androidx.room.Query(value = "DELETE FROM prodotti_in_ricette")
    public abstract void deleteAllProdottiInRicette();
}