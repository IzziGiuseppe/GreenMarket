package com.example.greenmarket.ui.altro.statistiche;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0015J&\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0014R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0019"}, d2 = {"Lcom/example/greenmarket/ui/altro/statistiche/StatsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "ricercaViewModel", "Lcom/example/greenmarket/ui/ricerca/RicercaViewModel;", "getRicercaViewModel", "()Lcom/example/greenmarket/ui/ricerca/RicercaViewModel;", "ricercaViewModel$delegate", "Lkotlin/Lazy;", "statsViewModel", "Lcom/example/greenmarket/ui/altro/statistiche/StatsViewModel;", "getStatsViewModel", "()Lcom/example/greenmarket/ui/altro/statistiche/StatsViewModel;", "statsViewModel$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "startProdotto", "nome", "", "descrizione", "prezzo", "", "foto", "app_debug"})
public final class StatsActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy statsViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy ricercaViewModel$delegate = null;
    
    public StatsActivity() {
        super();
    }
    
    private final com.example.greenmarket.ui.altro.statistiche.StatsViewModel getStatsViewModel() {
        return null;
    }
    
    private final com.example.greenmarket.ui.ricerca.RicercaViewModel getRicercaViewModel() {
        return null;
    }
    
    @java.lang.Override
    @android.annotation.SuppressLint(value = {"MissingInflatedId"})
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    public final void startProdotto(@org.jetbrains.annotations.NotNull
    java.lang.String nome, @org.jetbrains.annotations.NotNull
    java.lang.String descrizione, float prezzo, @org.jetbrains.annotations.NotNull
    java.lang.String foto) {
    }
}