package com.example.greenmarket.ui.login;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/example/greenmarket/ui/login/RegisterActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/greenmarket/databinding/ActivityRegistrazioneBinding;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "createTextWatcher", "Landroid/text/TextWatcher;", "validationFunction", "Lkotlin/Function0;", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "validateAddress", "editText", "Landroid/widget/EditText;", "validateEmail", "validateInputField", "validatePassword", "app_debug"})
public final class RegisterActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.greenmarket.databinding.ActivityRegistrazioneBinding binding;
    private com.google.firebase.auth.FirebaseAuth firebaseAuth;
    private com.google.firebase.firestore.FirebaseFirestore db;
    
    public RegisterActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final android.text.TextWatcher createTextWatcher(kotlin.jvm.functions.Function0<java.lang.Boolean> validationFunction) {
        return null;
    }
    
    private final boolean validateInputField(android.widget.EditText editText) {
        return false;
    }
    
    private final boolean validateAddress(android.widget.EditText editText) {
        return false;
    }
    
    private final boolean validateEmail(android.widget.EditText editText) {
        return false;
    }
    
    private final boolean validatePassword(android.widget.EditText editText) {
        return false;
    }
}