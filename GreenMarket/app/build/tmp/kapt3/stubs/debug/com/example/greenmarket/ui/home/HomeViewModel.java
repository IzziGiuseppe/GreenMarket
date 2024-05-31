package com.example.greenmarket.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u001f\u001a\u00020 J\u001f\u0010!\u001a\u00020 2\u0012\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b\u00a2\u0006\u0002\u0010\"J\u0006\u0010#\u001a\u00020 J \u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0007J\u000e\u0010+\u001a\u00020 2\u0006\u0010,\u001a\u00020\fJ\b\u0010-\u001a\u00020 H\u0007J\u0010\u0010.\u001a\u00020 2\u0006\u0010)\u001a\u00020*H\u0007R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\b0\b0\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00168F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0018\"\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018\u00a8\u0006/"}, d2 = {"Lcom/example/greenmarket/ui/home/HomeViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_listaProdotti", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/greenmarket/db/model/Prodotto;", "_prodotto", "kotlin.jvm.PlatformType", "_status", "", "_text", "db", "Lcom/example/greenmarket/db/GMDatabase;", "dbf", "Lcom/google/firebase/firestore/FirebaseFirestore;", "listaProdotti", "getListaProdotti", "()Landroidx/lifecycle/MutableLiveData;", "prodotto", "Landroidx/lifecycle/LiveData;", "getProdotto", "()Landroidx/lifecycle/LiveData;", "status", "getStatus", "setStatus", "(Landroidx/lifecycle/LiveData;)V", "text", "getText", "deleteAllProdotti", "", "insert", "([Lcom/example/greenmarket/db/model/Prodotto;)V", "readAllProdotti", "readNome", "fragment", "Lcom/example/greenmarket/ui/home/HomeFragment;", "imageProfilo", "Landroid/widget/ImageView;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "readProdotto", "nome", "updateStatus", "updateStatusRegularly", "app_debug"})
public final class HomeViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _text = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> text = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.greenmarket.db.GMDatabase db = null;
    @org.jetbrains.annotations.NotNull
    private com.google.firebase.firestore.FirebaseFirestore dbf;
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.MutableLiveData<com.example.greenmarket.db.model.Prodotto> _prodotto;
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.MutableLiveData<com.example.greenmarket.db.model.Prodotto[]> _listaProdotti;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _status = null;
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.LiveData<java.lang.String> status;
    
    public HomeViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.greenmarket.db.model.Prodotto> getProdotto() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.example.greenmarket.db.model.Prodotto[]> getListaProdotti() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getStatus() {
        return null;
    }
    
    public final void setStatus(@org.jetbrains.annotations.NotNull
    androidx.lifecycle.LiveData<java.lang.String> p0) {
    }
    
    public final void readProdotto(@org.jetbrains.annotations.NotNull
    java.lang.String nome) {
    }
    
    public final void readAllProdotti() {
    }
    
    public final void insert(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.db.model.Prodotto... prodotto) {
    }
    
    public final void deleteAllProdotti() {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    public final void readNome(@org.jetbrains.annotations.NotNull
    com.example.greenmarket.ui.home.HomeFragment fragment, @org.jetbrains.annotations.NotNull
    android.widget.ImageView imageProfilo, @org.jetbrains.annotations.NotNull
    kotlinx.coroutines.CoroutineScope scope) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    public final void updateStatus() {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    public final void updateStatusRegularly(@org.jetbrains.annotations.NotNull
    kotlinx.coroutines.CoroutineScope scope) {
    }
}