package com.example.greenmarket.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.greenmarket.R
import com.example.greenmarket.databinding.ActivityRegistrazioneBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrazioneBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrazioneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        binding.buttonRegister.setOnClickListener{
            val name = binding.editTextNome.text.toString().trim()
            val surname = binding.editTextCognome.text.toString().trim()
            val index = binding.editTextIndirizzo.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

            if(name.isNotEmpty() && surname.isNotEmpty() && index.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if(password == confirmPassword){
                    //Salvataggio dati utente
                    val userMap = hashMapOf(
                        "nome" to name,
                        "cognome" to surname,
                        "indirizzo" to index,
                        "email" to email,
                        "password" to password
                    )

                    val userId = FirebaseAuth.getInstance().currentUser!!.uid
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                    db.collection("users").document(userId).set(userMap)
                        .addOnSuccessListener{
                            Toast.makeText(this, "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show()
                            binding.editTextNome.text.clear()
                            binding.editTextCognome.text.clear()
                            binding.editTextIndirizzo.text.clear()
                            binding.editTextEmail.text.clear()
                            binding.editTextPassword.text.clear()
                            binding.editTextConfirmPassword.text.clear()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }
                        .addOnFailureListener{
                            Toast.makeText(this, "Errore durante la registrazione", Toast.LENGTH_SHORT).show()
                        }
                }else{
                    Toast.makeText(this, "Password non corrispondenti", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textViewAccedi.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}