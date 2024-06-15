package com.example.greenmarket.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0017J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\rH\u0016J\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\rH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/greenmarket/ui/home/HomeFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/example/greenmarket/ui/home/OnImageClickListener;", "()V", "_binding", "Lcom/example/greenmarket/databinding/FragmentHomeBinding;", "adapter", "Lcom/example/greenmarket/ui/home/ImagePagerAdapter;", "binding", "getBinding", "()Lcom/example/greenmarket/databinding/FragmentHomeBinding;", "imageList", "", "", "navController", "Landroidx/navigation/NavController;", "nomi", "", "nomiRicette", "prezzi", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onImageClick", "position", "onImageRicetteClick", "app_debug"})
public final class HomeFragment extends androidx.fragment.app.Fragment implements com.example.greenmarket.ui.home.OnImageClickListener {
    @org.jetbrains.annotations.Nullable
    private com.example.greenmarket.databinding.FragmentHomeBinding _binding;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.Integer> imageList = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> prezzi = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> nomi = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> nomiRicette = null;
    private com.example.greenmarket.ui.home.ImagePagerAdapter adapter;
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
    
    @java.lang.Override
    public void onImageClick(int position) {
    }
    
    @java.lang.Override
    public void onImageRicetteClick(int position) {
    }
}