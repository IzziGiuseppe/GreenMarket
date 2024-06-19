package com.example.greenmarket.ui.login;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0017J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/example/greenmarket/ui/login/LoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/greenmarket/databinding/ActivityLoginBinding;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "createTextWatcher", "Landroid/text/TextWatcher;", "validationFunction", "Lkotlin/Function0;", "", "isInternetAvailable", "context", "Landroid/content/Context;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "validateEmail", "editText", "Landroid/widget/EditText;", "validatePassword", "app_debug"})
public final class LoginActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.greenmarket.databinding.ActivityLoginBinding binding;
    private com.google.firebase.auth.FirebaseAuth firebaseAuth;
    
    public LoginActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    @java.lang.Deprecated
    public void onBackPressed() {
    }
    
    private final boolean isInternetAvailable(android.content.Context context) {
        return false;
    }
    
    private final android.text.TextWatcher createTextWatcher(kotlin.jvm.functions.Function0<java.lang.Boolean> validationFunction) {
        return null;
    }
    
    private final boolean validateEmail(android.widget.EditText editText) {
        return false;
    }
    
    private final boolean validatePassword(android.widget.EditText editText) {
        return false;
    }
}