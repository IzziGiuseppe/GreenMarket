package com.example.greenmarket.ui.login.reset_password

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.greenmarket.databinding.ActivityResetPasswordBinding
import com.example.greenmarket.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {
    private val resetPasswordViewModel: ResetPasswordViewModel by viewModels()

    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val email = binding.editTextResetPass
        email.addTextChangedListener(resetPasswordViewModel.createTextWatcher { resetPasswordViewModel.validateEmail(email) })

        binding.buttonResetPass.setOnClickListener{
            val isEmailValid = resetPasswordViewModel.validateEmail(email)
            if(isEmailValid){
                resetPasswordViewModel.resetPassword(email.text.toString())
                resetPasswordViewModel.resetSuccess.observe(this){resetSuccess->
                    if(resetSuccess){
                        Toast.makeText(this, "Email inviata", Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(this, "Errore durante l'invio", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.textViewTornaLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}