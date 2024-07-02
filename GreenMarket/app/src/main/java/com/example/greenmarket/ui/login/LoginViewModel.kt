package com.example.greenmarket.ui.login

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greenmarket.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestore

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var firebaseAuth = FirebaseAuth.getInstance()

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    fun creazioneUserAccount(email: String, pass: String) {
        firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener{
                _loginSuccess.value = true
            }
            .addOnFailureListener{
                _loginSuccess.value = false
                when (it) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        Toast.makeText(getApplication(), "Credenziali non valide", Toast.LENGTH_SHORT).show()
                    }
                    is FirebaseAuthInvalidUserException -> { Toast.makeText(getApplication(), "Utente non esistente", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(getApplication(), "Errore nell'inserimento di email/password", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    //Crea un TextWatcher generico che accetta una funzione di validazione e la esegue ogni volta che il testo cambia.
    fun createTextWatcher(validationFunction: () -> Boolean): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validationFunction()
            }
        }
    }

    //Funzione che verifica se l'email inserita è valida
    fun validateEmail(editText: EditText): Boolean {
        val email = editText.text.toString().trim()
        return if (email.isEmpty()) {
            editText.error = "Questo campo è obbligatorio"
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editText.error = "Email non valida"
            false
        } else if(email.length > 30){
            editText.error = "L'email può contenere al massimo 30 caratteri"
            false
        } else {
            true
        }
    }

    //Funzione che verifica se la password inserita è valida
    fun validatePassword(editText: EditText): Boolean {
        val password = editText.text.toString().trim()
        return if (password.isEmpty()) {
            editText.error = "Questo campo è obbligatorio"
            false
        } else if (password.length < 8) {
            editText.error = "La password deve contenere almeno 8 caratteri"
            false
        } else {
            true
        }
    }
}