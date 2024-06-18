package com.example.greenmarket.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.greenmarket.R
import com.example.greenmarket.databinding.ActivityResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val email = binding.editTextResetPass
        email.addTextChangedListener(createTextWatcher { validateEmail(email) })

        binding.buttonResetPass.setOnClickListener{
            val isEmailValid = validateEmail(email)
            if(isEmailValid){
                auth.sendPasswordResetEmail(email.text.toString())
                    .addOnSuccessListener {
                        Toast.makeText(this, "Email inviata", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener{
                        Toast.makeText(this, "Errore durante l'invio", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        binding.textViewTornaLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
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
}