package com.example.greenmarket.ui.login;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u001c\u001a\u00020\u001d2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0010H\u0002J\u0012\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\u001dH\u0002J\b\u0010\"\u001a\u00020\u001dH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006#"}, d2 = {"Lcom/example/greenmarket/ui/login/UserProfileActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/greenmarket/databinding/ActivityProfiloUtenteBinding;", "currentUser", "Lcom/google/firebase/auth/FirebaseUser;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "homeViewModel", "Lcom/example/greenmarket/ui/home/HomeViewModel;", "getHomeViewModel", "()Lcom/example/greenmarket/ui/home/HomeViewModel;", "homeViewModel$delegate", "Lkotlin/Lazy;", "originalData", "", "", "", "storageRef", "Lcom/google/firebase/storage/StorageReference;", "uri", "Landroid/net/Uri;", "userProfileViewModel", "Lcom/example/greenmarket/ui/login/UserProfileViewModel;", "getUserProfileViewModel", "()Lcom/example/greenmarket/ui/login/UserProfileViewModel;", "userProfileViewModel$delegate", "cancelChanges", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showDeleteAccountConfirmationDialog", "showLogoutConfirmationDialog", "app_debug"})
public final class UserProfileActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.greenmarket.databinding.ActivityProfiloUtenteBinding binding;
    private com.google.firebase.firestore.FirebaseFirestore db;
    @org.jetbrains.annotations.Nullable
    private final com.google.firebase.auth.FirebaseUser currentUser = null;
    @org.jetbrains.annotations.Nullable
    private java.util.Map<java.lang.String, ? extends java.lang.Object> originalData;
    @org.jetbrains.annotations.NotNull
    private com.google.firebase.storage.StorageReference storageRef;
    @org.jetbrains.annotations.Nullable
    private android.net.Uri uri;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy userProfileViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy homeViewModel$delegate = null;
    
    public UserProfileActivity() {
        super();
    }
    
    private final com.example.greenmarket.ui.login.UserProfileViewModel getUserProfileViewModel() {
        return null;
    }
    
    private final com.example.greenmarket.ui.home.HomeViewModel getHomeViewModel() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void cancelChanges(java.util.Map<java.lang.String, ? extends java.lang.Object> originalData) {
    }
    
    private final void showLogoutConfirmationDialog() {
    }
    
    private final void showDeleteAccountConfirmationDialog() {
    }
}