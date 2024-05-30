package com.example.greenmarket.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrazioneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.buttonRegister.setOnClickListener{
            val name = binding.editTextNome.text.toString().trim()
            val surname = binding.editTextCognome.text.toString().trim()
            val address = binding.editTextIndirizzo.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()
            val photo = "https://imgur.com/a/AJkXyR3"

            if(name.isNotEmpty() && surname.isNotEmpty() && address.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if(password == confirmPassword){
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener {
                            val userID = firebaseAuth.currentUser?.uid
                            Log.d("UserProfileActivity", "User ID: $userID")  // Log l'ID dell'utente corrente
                            if (userID != null) {
                                //Salvataggio dati utente
                                val userMap = hashMapOf(
                                    "nome" to name,
                                    "cognome" to surname,
                                    "indirizzo" to address,
                                    "foto" to photo
                                )
                                db.collection("users").document(userID).set(userMap)
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
                            }
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