package com.example.greenmarket.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import java.util.regex.Pattern
import androidx.appcompat.app.AppCompatActivity
import com.example.greenmarket.databinding.ActivityRegistrazioneBinding
import com.example.greenmarket.ui.altro.statistiche.ProdottoInStatsModel
import com.example.greenmarket.ui.altro.termini_condizioni.TermCondActivity
import com.example.greenmarket.ui.lista_spesa.ProdottoInListaModel
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
            val photo = "https://firebasestorage.googleapis.com/v0/b/greenmarket-1f99c.appspot.com/o/immagine_profilo_default.jpg?alt=media&token=785fd544-696d-4d81-a51b-57fbd52f05ab"

            if(name.isNotEmpty() && surname.isNotEmpty() && address.isNotEmpty() && (isValidEmail(email)) && password.isNotEmpty()
                && confirmPassword.isNotEmpty()){
                if(password == confirmPassword){
                    if(binding.checkBox.isChecked){
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

                                    val prodotti: Map<String?, List<Float>?> = emptyMap()
                                    //Creazione lista della spesa associata all'utente
                                    val listaSpesa = hashMapOf(
                                        "data" to null,
                                        "valido" to false,
                                        "prodotti" to prodotti
                                    )

                                    val tesseraPunti = hashMapOf(
                                        "saldo" to 0f,
                                        "punti" to 0
                                    )

                                    //Statistiche
                                    val mele = ProdottoInStatsModel("Mele", 0f)
                                    val banane = ProdottoInStatsModel("Banane", 0f)
                                    val pomodori = ProdottoInStatsModel("Pomodori", 0f)
                                    val carote = ProdottoInStatsModel("Carote", 0f)
                                    val pere = ProdottoInStatsModel("Pere", 0f)
                                    val melanzane = ProdottoInStatsModel("Melanzane", 0f)
                                    val spinaci = ProdottoInStatsModel("Spinaci", 0f)
                                    val limoni = ProdottoInStatsModel("Limoni", 0f)
                                    val arance = ProdottoInStatsModel("Arance", 0f)
                                    val patate = ProdottoInStatsModel("Patate", 0f)

                                    val prodottiStats = mapOf(
                                        "Mele" to mele.quantitaTot,
                                        "Banane" to banane.quantitaTot,
                                        "Pomodori" to pomodori.quantitaTot,
                                        "Carote" to carote.quantitaTot,
                                        "Pere" to pere.quantitaTot,
                                        "Melanzane" to melanzane.quantitaTot,
                                        "Spinaci" to spinaci.quantitaTot,
                                        "Limoni" to limoni.quantitaTot,
                                        "Arance" to arance.quantitaTot,
                                        "Patate" to patate.quantitaTot

                                    )

                                    db.collection("users").document(userID).set(userMap)
                                        .addOnSuccessListener{
                                            binding.editTextNome.text.clear()
                                            binding.editTextCognome.text.clear()
                                            binding.editTextIndirizzo.text.clear()
                                            binding.editTextEmail.text.clear()
                                            binding.editTextPassword.text.clear()
                                            binding.editTextConfirmPassword.text.clear()
                                            db.collection("users").document(userID).collection("historical").document("shoppingList").set(listaSpesa)
                                                .addOnSuccessListener{
                                                    db.collection("users").document(userID).collection("pointCard").document("wallet").set(tesseraPunti)
                                                        .addOnSuccessListener{
                                                            db.collection("users").document(userID).collection("stats").document("top_selling_products").set(prodottiStats)
                                                            Toast.makeText(this, "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show()
                                                            val intent = Intent(this, LoginActivity::class.java)
                                                            startActivity(intent)
                                                        }
                                                        .addOnFailureListener{
                                                            Toast.makeText(this, "Errore durante la creazione della tessera a punti", Toast.LENGTH_SHORT).show()
                                                        }
                                                }
                                                .addOnFailureListener{
                                                    Toast.makeText(this, "Errore durante la creazione della lista della spesa", Toast.LENGTH_SHORT).show()
                                                }
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
                        Toast.makeText(this, "Accetta termini e condizioni d'uso", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Password non corrispondenti", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Compila tutti i campi correttamente", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textViewAccedi.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.textViewTerminiCondizioni.setOnClickListener{
            val intent = Intent(this, TermCondActivity::class.java)
            startActivity(intent)
        }
    }

    //Funzione che verifica se l'email inserita è valida rispetto al formato corretto
    private fun isValidEmail(email: String?): Boolean {
        if (email.isNullOrEmpty()) {
            return false
        }

        val emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"
        val pattern = Pattern.compile(emailPattern)
        val matcher = pattern.matcher(email)

        return matcher.matches()
    }
}