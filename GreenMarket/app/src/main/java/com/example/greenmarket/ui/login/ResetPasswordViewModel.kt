package com.example.greenmarket.ui.login

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordViewModel(application: Application) : AndroidViewModel(application) {

    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun resetListener(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                Toast.makeText(getApplication(), "Email inviata", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(getApplication(), "Email errata", Toast.LENGTH_SHORT).show()
            }
    }
}