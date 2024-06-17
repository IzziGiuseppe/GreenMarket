package com.example.greenmarket.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0017J\b\u0010\u0015\u001a\u00020\u0016H\u0016J&\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0019J,\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u00192\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00190\u000bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/example/greenmarket/ui/home/HomeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/greenmarket/databinding/FragmentHomeBinding;", "binding", "getBinding", "()Lcom/example/greenmarket/databinding/FragmentHomeBinding;", "navController", "Landroidx/navigation/NavController;", "prodottiRV", "", "Lcom/example/greenmarket/db/model/Prodotto;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "startProdotto", "nome", "", "descrizione", "prezzo", "", "foto", "startRicetta", "ingredienti", "app_debug"})
public final class HomeFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable
    private com.example.greenmarket.databinding.FragmentHomeBinding _binding;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.greenmarket.db.model.Prodotto> prodottiRV = null;
    private androidx.navigation.NavController navController;
    
    public HomeFragment() {
        super();
    }
    
    private final com.example.greenmarket.databinding.FragmentHomeBinding getBinding() {
        return null;
    }
    
    @java.lang.Override
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    @org.jetbrains.annotations.NotNull
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
    
    public final void startRicetta(@org.jetbrains.annotations.NotNull
    java.lang.String nome, @org.jetbrains.annotations.NotNull
    java.lang.String descrizione, @org.jetbrains.annotations.NotNull
    java.lang.String foto, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> ingredienti) {
    }
    
    public final void startProdotto(@org.jetbrains.annotations.NotNull
    java.lang.String nome, @org.jetbrains.annotations.NotNull
    java.lang.String descrizione, float prezzo, @org.jetbrains.annotations.NotNull
    java.lang.String foto) {
    }
}