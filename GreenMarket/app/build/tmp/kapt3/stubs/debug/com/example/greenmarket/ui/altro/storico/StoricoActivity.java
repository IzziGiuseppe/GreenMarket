package com.example.greenmarket.ui.altro.storico;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010 \n\u0002\u0010\u0007\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0015JF\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u001c\u0010\u0010\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00120\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fH\u0003R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0017"}, d2 = {"Lcom/example/greenmarket/ui/altro/storico/StoricoActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "storicoViewModel", "Lcom/example/greenmarket/ui/altro/storico/StoricoViewModel;", "getStoricoViewModel", "()Lcom/example/greenmarket/ui/altro/storico/StoricoViewModel;", "storicoViewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "startProdotto", "data", "", "prodotti", "", "", "", "totale", "codiceSconto", "valoreSconto", "app_debug"})
public final class StoricoActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy storicoViewModel$delegate = null;
    
    public StoricoActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.greenmarket.ui.altro.storico.StoricoViewModel getStoricoViewModel() {
        return null;
    }
    
    @java.lang.Override
    @androidx.annotation.OptIn(markerClass = {androidx.media3.common.util.UnstableApi.class})
    @android.annotation.SuppressLint(value = {"MissingInflatedId"})
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @androidx.annotation.OptIn(markerClass = {androidx.media3.common.util.UnstableApi.class})
    private final void startProdotto(java.lang.String data, java.util.Map<java.lang.String, ? extends java.util.List<java.lang.Float>> prodotti, float totale, java.lang.String codiceSconto, java.lang.String valoreSconto) {
    }
}