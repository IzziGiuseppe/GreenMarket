package com.example.greenmarket.ui.login

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.greenmarket.MainActivity
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

        val progressBar = binding.progressBarLogin

        binding.textViewRegistratiLogin.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.textViewPassDimenticata.setOnClickListener{
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        val email = binding.editTextEmailLogin
        val pass = binding.editTextPasswordLogin

        email.addTextChangedListener(createTextWatcher { validateEmail(email) })
        pass.addTextChangedListener(createTextWatcher { validatePassword(pass) })

        binding.buttonAccediLogin.setOnClickListener{
            val isEmailValid = validateEmail(email)
            val isPasswordValid = validatePassword(pass)

            if(isEmailValid && isPasswordValid){
                if (isInternetAvailable(this)) {
                    binding.buttonAccediLogin.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE

                    val handler = Handler(Looper.getMainLooper())
                    val timeoutRunnable = Runnable {
                        // Se il timeout viene raggiunto, mostra il messaggio di errore
                        Toast.makeText(this, "Connessione instabile. Attendere...", Toast.LENGTH_LONG).show()
                    }

                    // Posticipa il runnable del timeout di 5 secondi
                    handler.postDelayed(timeoutRunnable, 5000)
                    firebaseAuth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString())
                        .addOnSuccessListener{
                            handler.removeCallbacks(timeoutRunnable)
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        .addOnFailureListener{
                            handler.removeCallbacks(timeoutRunnable)
                            binding.buttonAccediLogin.visibility = View.VISIBLE
                            binding.progressBarLogin.visibility = View.GONE
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

    //Crea un TextWatcher generico che accetta una funzione di validazione e la esegue ogni volta che il testo cambia.
    private fun createTextWatcher(validationFunction: () -> Boolean): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validationFunction()
            }
        }
    }

    //Funzione che verifica se l'email inserita è valida
    private fun validateEmail(editText: EditText): Boolean {
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
    private fun validatePassword(editText: EditText): Boolean {
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