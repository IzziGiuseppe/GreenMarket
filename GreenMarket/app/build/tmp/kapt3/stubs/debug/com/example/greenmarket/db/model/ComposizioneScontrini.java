package com.example.greenmarket.db.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0018"}, d2 = {"Lcom/example/greenmarket/db/model/ComposizioneScontrini;", "", "scontrino", "", "prodotto", "", "quantita", "", "(ILjava/lang/String;F)V", "getProdotto", "()Ljava/lang/String;", "getQuantita", "()F", "getScontrino", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "composizione_scontrini", primaryKeys = {"scontrino", "prodotto"}, foreignKeys = {@androidx.room.ForeignKey(entity = com.example.greenmarket.db.model.Scontrino.class, parentColumns = {"_id"}, childColumns = {"scontrino"}, onDelete = 5), @androidx.room.ForeignKey(entity = com.example.greenmarket.db.model.Prodotto.class, parentColumns = {"nome"}, childColumns = {"prodotto"}, onDelete = 1)})
public final class ComposizioneScontrini {
    private final int scontrino = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String prodotto = null;
    private final float quantita = 0.0F;
    
    public ComposizioneScontrini(int scontrino, @org.jetbrains.annotations.NotNull
    java.lang.String prodotto, float quantita) {
        super();
    }
    
    public final int getScontrino() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getProdotto() {
        return null;
    }
    
    public final float getQuantita() {
        return 0.0F;
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.greenmarket.db.model.ComposizioneScontrini copy(int scontrino, @org.jetbrains.annotations.NotNull
    java.lang.String prodotto, float quantita) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}