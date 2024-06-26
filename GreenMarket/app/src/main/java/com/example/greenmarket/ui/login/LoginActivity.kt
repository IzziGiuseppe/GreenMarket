package com.example.greenmarket.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.greenmarket.InternetTest
import com.example.greenmarket.MainActivity
import com.example.greenmarket.R
import com.example.greenmarket.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val progressBar = binding.progressBarLogin

        val iT = InternetTest()

        //Variabile utile per gestire la visualizzazione della password
        var isPasswordVisible = false

        binding.togglePasswordVisibility.setOnClickListener {
            if (isPasswordVisible) {
                // Nascondi password
                binding.editTextPasswordLogin.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                //Cambia l'icona della visualizzazione
                binding.togglePasswordVisibility.setImageResource(R.mipmap.ic_visibility_off)
            } else {
                // Mostra password
                binding.editTextPasswordLogin.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                //Cambia l'icona della visualizzazione
                binding.togglePasswordVisibility.setImageResource(R.mipmap.ic_visibility)
            }
            // Sposta il cursore alla fine del testo
            binding.editTextPasswordLogin.setSelection(binding.editTextPasswordLogin.text.length)
            isPasswordVisible = !isPasswordVisible
        }

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

        email.addTextChangedListener(loginViewModel.createTextWatcher { loginViewModel.validateEmail(email) })
        pass.addTextChangedListener(loginViewModel.createTextWatcher { loginViewModel.validatePassword(pass) })

        val handler = Handler(Looper.getMainLooper())
        val timeoutRunnable = Runnable {
            // Se il timeout viene raggiunto, mostra il messaggio di errore
            Toast.makeText(
                this,
                "Connessione instabile. Attendere...",
                Toast.LENGTH_LONG
            ).show()
        }

        if (iT.isInternetAvailable(this)) {
            binding.buttonAccediLogin.setOnClickListener{
                val isEmailValid = loginViewModel.validateEmail(email)
                val isPasswordValid = loginViewModel.validatePassword(pass)

                if(isEmailValid && isPasswordValid) {
                    binding.buttonAccediLogin.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE

                    // Posticipa il runnable del timeout di 5 secondi
                    handler.postDelayed(timeoutRunnable, 5000)
                    loginViewModel.creazioneUserAccount(email.text.toString(), pass.text.toString())
                } else {
                    Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show()
                }
            }

            loginViewModel.loginSuccess.observe(this){ success ->
                progressBar.visibility = View.GONE
                binding.buttonAccediLogin.visibility = View.VISIBLE
                if (success) {
                    handler.removeCallbacks(timeoutRunnable)
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    handler.removeCallbacks(timeoutRunnable)
                    binding.buttonAccediLogin.visibility = View.VISIBLE
                    binding.progressBarLogin.visibility = View.GONE
                }
            }
        }else{
            iT.toast(this)
        }
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}