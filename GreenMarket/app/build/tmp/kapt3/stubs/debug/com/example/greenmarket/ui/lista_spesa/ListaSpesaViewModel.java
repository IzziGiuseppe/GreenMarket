package com.example.greenmarket.ui.lista_spesa;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u0007\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\nJ,\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u001c\u0010#\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\f\u0012\n\u0012\u0004\u0012\u00020%\u0018\u00010\u00050$H\u0002J\u0006\u0010&\u001a\u00020 J\u0006\u0010\'\u001a\u00020 J\u000e\u0010(\u001a\u00020 2\u0006\u0010\"\u001a\u00020\nR\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001a\u00a8\u0006)"}, d2 = {"Lcom/example/greenmarket/ui/lista_spesa/ListaSpesaViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_listaProdotti", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/greenmarket/ui/lista_spesa/ProdottoInListaModel;", "_listaSpesa", "Lcom/example/greenmarket/ui/lista_spesa/ListaDellaSpesaModel;", "_prezzo_totale_view", "", "_prodotto", "Lcom/example/greenmarket/ui/ricerca/ProdottoModel;", "_text", "currentUser", "Lcom/google/firebase/auth/FirebaseUser;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "listaProdotti", "getListaProdotti", "()Landroidx/lifecycle/MutableLiveData;", "listaSpesa", "getListaSpesa", "prezzo_totale_view", "Landroidx/lifecycle/LiveData;", "getPrezzo_totale_view", "()Landroidx/lifecycle/LiveData;", "prodotto", "getProdotto", "text", "getText", "deleteListaSpesa", "", "deleteProdByNome", "nome", "map", "", "", "readListaSpesa", "readPrezzoTotale", "readProdottoDettagliato", "app_debug"})
public final class ListaSpesaViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    @org.jetbrains.annotations.Nullable
    private final com.google.firebase.auth.FirebaseUser currentUser = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _text = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> text = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _prezzo_totale_view = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> prezzo_totale_view = null;
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.MutableLiveData<com.example.greenmarket.ui.lista_spesa.ListaDellaSpesaModel> _listaSpesa;
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.MutableLiveData<java.util.List<com.example.greenmarket.ui.lista_spesa.ProdottoInListaModel>> _listaProdotti;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.greenmarket.ui.ricerca.ProdottoModel> _prodotto = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.example.greenmarket.ui.ricerca.ProdottoModel> prodotto = null;
    
    public ListaSpesaViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getPrezzo_totale_view() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.example.greenmarket.ui.lista_spesa.ListaDellaSpesaModel> getListaSpesa() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.example.greenmarket.ui.lista_spesa.ProdottoInListaModel>> getListaProdotti() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.greenmarket.ui.ricerca.ProdottoModel> getProdotto() {
        return null;
    }
    
    public final void readProdottoDettagliato(@org.jetbrains.annotations.NotNull
    java.lang.String nome) {
    }
    
    public final void readListaSpesa() {
    }
    
    public final void deleteListaSpesa() {
    }
    
    private final java.util.List<com.example.greenmarket.ui.lista_spesa.ProdottoInListaModel> listaProdotti(java.util.Map<java.lang.String, ? extends java.util.List<java.lang.Float>> map) {
        return null;
    }
    
    public final void deleteProdByNome(@org.jetbrains.annotations.NotNull
    java.lang.String nome) {
    }
    
    public final void readPrezzoTotale() {
    }
}