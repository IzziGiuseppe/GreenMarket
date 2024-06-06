package com.example.greenmarket.ui.login

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.greenmarket.MainActivity
import com.example.greenmarket.R
import com.example.greenmarket.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import java.util.Locale

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.setLanguageCode(Locale.getDefault().language)

        binding.textViewRegistratiLogin.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.textViewPassDimenticata.setOnClickListener{
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAccediLogin.setOnClickListener{
            val email = binding.editTextEmailLogin.text.toString().trim()
            val pass = binding.editTextPasswordLogin.text.toString().trim()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                if (isInternetAvailable(this)) {
                    val handler = Handler(Looper.getMainLooper())
                    val timeoutRunnable = Runnable {
                        // Se il timeout viene raggiunto, mostra il messaggio di errore
                        Toast.makeText(this, "Connessione instabile. Attendere...", Toast.LENGTH_LONG).show()
                    }

                    // Posticipa il runnable del timeout di 5 secondi
                    handler.postDelayed(timeoutRunnable, 5000)
                    firebaseAuth.signInWithEmailAndPassword(email, pass)
                        .addOnSuccessListener{
                            handler.removeCallbacks(timeoutRunnable)
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }
                        .addOnFailureListener{
                            handler.removeCallbacks(timeoutRunnable)
                            //Toast.makeText(this, "Errore nell'inserimento di email/password", Toast.LENGTH_SHORT).show()
                            when (it) {
                                is FirebaseAuthInvalidCredentialsException -> {
                                    Toast.makeText(this, "Credenziali non valide", Toast.LENGTH_SHORT).show()
                                }
                                is FirebaseAuthInvalidUserException -> {
                                    Toast.makeText(this, "Utente non esistente", Toast.LENGTH_SHORT).show()
                                }
                                else -> {
                                    Toast.makeText(this, "Errore nell'inserimento di email/password", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                } else {
                    Toast.makeText(this, "Nessuna connessione Internet", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    //funzione che controlla se il dispositivo è connesso ad internet
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