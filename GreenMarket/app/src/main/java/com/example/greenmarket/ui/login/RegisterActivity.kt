package com.example.greenmarket.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern
import androidx.appcompat.app.AppCompatActivity
import com.example.greenmarket.InternetTest
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

        val iT = InternetTest()

        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val name = binding.editTextNome
        val surname = binding.editTextCognome
        val address = binding.editTextIndirizzo
        val email = binding.editTextEmail
        val password = binding.editTextPassword
        val confirmPassword = binding.editTextConfirmPassword
        val photo = "https://firebasestorage.googleapis.com/v0/b/greenmarket-1f99c.appspot.com/o/immagine_profilo_default.jpg?alt=media&token=785fd544-696d-4d81-a51b-57fbd52f05ab"

        //Aggiunge il TextWatcher a ciascun campo di testo in modo che venga validato in tempo reale
        name.addTextChangedListener(createTextWatcher { validateInputField(name) })
        surname.addTextChangedListener(createTextWatcher { validateInputField(surname) })
        address.addTextChangedListener(createTextWatcher { validateAddress(address) })
        email.addTextChangedListener(createTextWatcher { validateEmail(email) })
        password.addTextChangedListener(createTextWatcher { validatePassword(password) })

        binding.buttonRegister.setOnClickListener{
            if (iT.isInternetAvailable(this)) {
                //Controlliamo i campi del form di registrazione
                val isFirstNameValid = validateInputField(name)
                val isLastNameValid = validateInputField(surname)
                val isAddressValid = validateAddress(address)
                val isEmailValid = validateEmail(email)
                val isPasswordValid = validatePassword(password)
                val isConfirmPasswordValid = validatePassword(confirmPassword)

                if(isFirstNameValid && isLastNameValid && isAddressValid && isEmailValid && isPasswordValid
                    && isConfirmPasswordValid){
                    if(password == confirmPassword){
                        if(binding.checkBox.isChecked){
                            firebaseAuth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                                .addOnSuccessListener {
                                    val userID = firebaseAuth.currentUser?.uid
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

                                        val coupons = mutableListOf<String>()
                                        val setCS = hashMapOf(
                                            "codici sconto" to coupons
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
                                                                db.collection("users").document(userID).collection("pointCard").document("coupons").set(setCS)
                                                                    .addOnSuccessListener {
                                                                        db.collection("users").document(userID).collection("stats").document("top_selling_products").set(prodottiStats)
                                                                            .addOnSuccessListener {
                                                                                Toast.makeText(this, "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show()
                                                                                val intent = Intent(this, LoginActivity::class.java)
                                                                                startActivity(intent)
                                                                            }
                                                                            .addOnFailureListener{
                                                                                Toast.makeText(this, "Errore durante la creazione delle statistiche", Toast.LENGTH_SHORT).show()
                                                                            }
                                                                    }
                                                                    .addOnFailureListener{
                                                                        Toast.makeText(this, "Errore durante la creazione dei coupon", Toast.LENGTH_SHORT).show()
                                                                    }
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
            } else {
                iT.toast(this)
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

    //Funzione che verifica se il nome inserito è valido
    private fun validateInputField(editText: EditText): Boolean {
        val inputField = editText.text.toString().trim()
        return if (inputField.isEmpty()) {
            editText.error = "Questo campo è obbligatorio"
            false
        } else if (!inputField.matches(Regex("^[A-Za-zàèéìòù'\\s]+$"))) {
            editText.error = "Campo non valido"
            false
        } else if (inputField.length > 30) {
            editText.error = "Il campo può contenere al massimo 30 caratteri"
            false
        } else {
            true
        }
    }

    //Funzione che verifica se l'indirizzo inserito è valido
    private fun validateAddress(editText: EditText): Boolean {
        val address = editText.text.toString().trim()
        return if (address.isEmpty()) {
            editText.error = "Questo campo è obbligatorio"
            false
        }else if (!address.matches(Regex("^[A-Za-zàèéìòù0-9,.'\\s]+$"))) {
            editText.error = "Campo non valido"
            false
        } else if(address.length > 30){
            editText.error = "Il campo può contenere al massimo 30 caratteri"
            false
        } else{
            true
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