package com.example.greenmarket.ui.lista_spesa;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J,\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u001c\u0010\u000b\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t0\fH\u0002J$\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0017J\b\u0010\u0017\u001a\u00020\u0018H\u0016J0\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u000eH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u001f"}, d2 = {"Lcom/example/greenmarket/ui/lista_spesa/ListaSpesaFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/greenmarket/databinding/FragmentListaSpesaBinding;", "binding", "getBinding", "()Lcom/example/greenmarket/databinding/FragmentListaSpesaBinding;", "listaProdotti", "", "Lcom/example/greenmarket/ui/lista_spesa/ProdottoInListaModel;", "map", "", "", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "startProdotto", "nome", "descrizione", "prezzo", "foto", "quantita", "app_debug"})
public final class ListaSpesaFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable
    private com.example.greenmarket.databinding.FragmentListaSpesaBinding _binding;
    
    public ListaSpesaFragment() {
        super();
    }
    
    private final com.example.greenmarket.databinding.FragmentListaSpesaBinding getBinding() {
        return null;
    }
    
    @java.lang.Override
    @androidx.annotation.OptIn(markerClass = {androidx.media3.common.util.UnstableApi.class})
    @org.jetbrains.annotations.NotNull
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void startProdotto(java.lang.String nome, java.lang.String descrizione, float prezzo, java.lang.String foto, float quantita) {
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
    
    private final java.util.List<com.example.greenmarket.ui.lista_spesa.ProdottoInListaModel> listaProdotti(java.util.Map<java.lang.String, ? extends java.util.List<java.lang.Float>> map) {
        return null;
    }
}