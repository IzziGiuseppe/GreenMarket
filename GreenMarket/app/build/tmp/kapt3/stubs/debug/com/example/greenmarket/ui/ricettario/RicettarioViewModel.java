package com.example.greenmarket.ui.ricettario;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0018\u001a\u00020\u0019J\u001f\u0010\u001a\u001a\u00020\u00192\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0007\"\u00020\f\u00a2\u0006\u0002\u0010\u001bJ\u0006\u0010\u001c\u001a\u00020\u0019J\u000e\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u001fR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\b0\b0\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\f0\f0\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u00138F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u00138F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015\u00a8\u0006#"}, d2 = {"Lcom/example/greenmarket/ui/ricettario/RicettarioViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_listaRicette", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/greenmarket/db/model/ProdottiInRicette;", "_ricetta", "kotlin.jvm.PlatformType", "_ricettaDettagliata", "Lcom/example/greenmarket/db/model/Ricetta;", "db", "Lcom/example/greenmarket/db/GMDatabase;", "listaRicette", "getListaRicette", "()Landroidx/lifecycle/MutableLiveData;", "ricetta", "Landroidx/lifecycle/LiveData;", "getRicetta", "()Landroidx/lifecycle/LiveData;", "ricettaDettagliata", "getRicettaDettagliata", "deleteAllRicette", "", "insert", "([Lcom/example/greenmarket/db/model/Ricetta;)V", "readAllRicette", "readRicetta", "nome", "", "readRicettaDettagliata", "ricetteByProdotto", "prod", "app_debug"})
public final class RicettarioViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.greenmarket.db.GMDatabase db = null;
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.MutableLiveData<com.example.greenmarket.db.model.ProdottiInRicette> _ricetta;
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.MutableLiveData<com.example.greenmarket.db.model.Ricetta> _ricettaDettagliata;
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.MutableLiveData<com.example.greenmarket.db.model.ProdottiInRicette[]> _listaRicette;
    
    public RicettarioViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.greenmarket.db.model.ProdottiInRicette> getRicetta() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.greenmarket.db.model.Ricetta> getRicettaDettagliata() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.example.greenmarket.db.model.ProdottiInRicette[]> getListaRicette() {
        return null;
    }
    
    public final void readRicetta(@org.jetbrains.annotations.NotNull
    java.lang.String nome) {
    }
    
    public final void readRicettaDettagliata(@org.jetbrains.annotations.NotNull
    java.lang.String nome) {
    }
    
    public final void ricetteByProdotto(@org.jetbrains.annotations.NotNull
    java.lang.String prod) {
    }
    
    public final void readAllRicette() {
    }
    
    public final void insert(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.db.model.Ricetta... ricetta) {
    }
    
    public final void deleteAllRicette() {
    }
}