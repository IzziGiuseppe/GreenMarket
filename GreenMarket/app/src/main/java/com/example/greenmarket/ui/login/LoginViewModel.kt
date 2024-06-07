package com.example.greenmarket.ui.login

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    fun accediListener(email: String, pass: String) : Boolean {
        var flag = false
        if(email.isNotEmpty() && pass.isNotEmpty()){
            if (isInternetAvailable(getApplication())) {
                val handler = Handler(Looper.getMainLooper())
                val timeoutRunnable = Runnable {
                    // Se il timeout viene raggiunto, mostra il messaggio di errore
                    Toast.makeText(getApplication(), "Connessione instabile. Attendere...", Toast.LENGTH_LONG).show()
                }

                // Posticipa il runnable del timeout di 5 secondi
                handler.postDelayed(timeoutRunnable, 5000)
                firebaseAuth.signInWithEmailAndPassword(email, pass)
                    .addOnSuccessListener{
                        handler.removeCallbacks(timeoutRunnable)
                        flag = true
                    }
                    .addOnFailureListener{
                        handler.removeCallbacks(timeoutRunnable)
                        when (it) {
                            is FirebaseAuthInvalidCredentialsException -> {
                                Toast.makeText(getApplication(), "Credenziali non valide", Toast.LENGTH_SHORT).show()
                            }
                            is FirebaseAuthInvalidUserException -> {
                                Toast.makeText(getApplication(), "Utente non esistente", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                Toast.makeText(getApplication(), "Errore nell'inserimento di email/password", Toast.LENGTH_SHORT).show()
                            }
                        }
                        flag = false
                    }
            } else {
                Toast.makeText(getApplication(), "Nessuna connessione Internet", Toast.LENGTH_SHORT).show()
                flag = false
            }
        } else {
            Toast.makeText(getApplication(), "Compila tutti i campi", Toast.LENGTH_SHORT).show()
            flag = false
        }
        return flag
    }

    //Funzione che gestisce la connessione ad internet
    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}