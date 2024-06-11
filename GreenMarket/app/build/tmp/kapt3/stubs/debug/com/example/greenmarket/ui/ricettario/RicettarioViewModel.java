package com.example.greenmarket.ui.ricettario;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001bR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0012\u00a8\u0006\u001f"}, d2 = {"Lcom/example/greenmarket/ui/ricettario/RicettarioViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_listaRicette", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/greenmarket/ui/ricettario/RicettaModel;", "_ricetta", "_ricettaDettagliata", "Lcom/example/greenmarket/db/model/Ricetta;", "kotlin.jvm.PlatformType", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "listaRicette", "Landroidx/lifecycle/LiveData;", "getListaRicette", "()Landroidx/lifecycle/LiveData;", "ricetta", "getRicetta", "ricettaDettagliata", "getRicettaDettagliata", "readAllRicette", "", "readRicetta", "nome", "", "readRicettaDettagliata", "ricetteByProdotto", "prod", "app_debug"})
public final class RicettarioViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.MutableLiveData<com.example.greenmarket.ui.ricettario.RicettaModel> _ricetta;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.example.greenmarket.ui.ricettario.RicettaModel> ricetta = null;
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.MutableLiveData<com.example.greenmarket.db.model.Ricetta> _ricettaDettagliata;
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.MutableLiveData<java.util.List<com.example.greenmarket.ui.ricettario.RicettaModel>> _listaRicette;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.example.greenmarket.ui.ricettario.RicettaModel>> listaRicette = null;
    
    public RicettarioViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.greenmarket.ui.ricettario.RicettaModel> getRicetta() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.greenmarket.db.model.Ricetta> getRicettaDettagliata() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.greenmarket.ui.ricettario.RicettaModel>> getListaRicette() {
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
}