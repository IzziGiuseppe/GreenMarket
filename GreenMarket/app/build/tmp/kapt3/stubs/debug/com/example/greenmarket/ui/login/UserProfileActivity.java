package com.example.greenmarket.ui.login;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0011\u001a\u00020\u00122\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\nH\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0012\u0010\u0017\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0012H\u0002J\b\u0010\u001b\u001a\u00020\u0012H\u0002J\b\u0010\u001c\u001a\u00020\u0012H\u0002J(\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/greenmarket/ui/login/UserProfileActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/greenmarket/databinding/ActivityProfiloUtenteBinding;", "currentUser", "Lcom/google/firebase/auth/FirebaseUser;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "originalData", "", "", "", "storageRef", "Lcom/google/firebase/storage/StorageReference;", "uri", "Landroid/net/Uri;", "cancelChanges", "", "deleteAccount", "getHash", "bytes", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "saveData", "showDeleteAccountConfirmationDialog", "showLogoutConfirmationDialog", "uploadNewImage", "user", "app_debug"})
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
    
    public UserProfileActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void saveData() {
    }
    
    private final void uploadNewImage(android.net.Uri uri, com.google.firebase.auth.FirebaseUser user, com.google.firebase.storage.StorageReference storageRef, com.google.firebase.firestore.FirebaseFirestore db) {
    }
    
    private final java.lang.String getHash(byte[] bytes) {
        return null;
    }
    
    private final void cancelChanges(java.util.Map<java.lang.String, ? extends java.lang.Object> originalData) {
    }
    
    private final void deleteAccount() {
    }
    
    private final void showLogoutConfirmationDialog() {
    }
    
    private final void showDeleteAccountConfirmationDialog() {
    }
}