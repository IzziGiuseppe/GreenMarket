package com.example.greenmarket.ui.altro.chi_siamo;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0015J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J.\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/example/greenmarket/ui/altro/chi_siamo/ChiSiamoActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "()V", "chiSiamoViewModel", "Lcom/example/greenmarket/ui/altro/chi_siamo/ChiSiamoViewModel;", "getChiSiamoViewModel", "()Lcom/example/greenmarket/ui/altro/chi_siamo/ChiSiamoViewModel;", "chiSiamoViewModel$delegate", "Lkotlin/Lazy;", "googleMap", "Lcom/google/android/gms/maps/GoogleMap;", "mapView", "Lcom/google/android/gms/maps/MapView;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMapReady", "p0", "startSoci", "linkedin", "", "github", "outlook", "socio", "foto", "app_debug"})
public final class ChiSiamoActivity extends androidx.appcompat.app.AppCompatActivity implements com.google.android.gms.maps.OnMapReadyCallback {
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy chiSiamoViewModel$delegate = null;
    private com.google.android.gms.maps.MapView mapView;
    private com.google.android.gms.maps.GoogleMap googleMap;
    
    public ChiSiamoActivity() {
        super();
    }
    
    private final com.example.greenmarket.ui.altro.chi_siamo.ChiSiamoViewModel getChiSiamoViewModel() {
        return null;
    }
    
    @java.lang.Override
    @android.annotation.SuppressLint(value = {"MissingInflatedId"})
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onMapReady(@org.jetbrains.annotations.NotNull
    com.google.android.gms.maps.GoogleMap p0) {
    }
    
    public final void startSoci(@org.jetbrains.annotations.NotNull
    java.lang.String linkedin, @org.jetbrains.annotations.NotNull
    java.lang.String github, @org.jetbrains.annotations.NotNull
    java.lang.String outlook, @org.jetbrains.annotations.NotNull
    java.lang.String socio, @org.jetbrains.annotations.NotNull
    java.lang.String foto) {
    }
}