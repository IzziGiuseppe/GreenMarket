package com.example.greenmarket.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.greenmarket.InternetTest
import com.example.greenmarket.R
import com.example.greenmarket.databinding.ActivityRegistrazioneBinding
import com.example.greenmarket.db.model.ProdottoInStatsModel
import com.example.greenmarket.ui.altro.termini_condizioni.TermCondActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private val registerViewModel: RegisterViewModel by viewModels()

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
        name.addTextChangedListener(registerViewModel.createTextWatcher { registerViewModel.validateInputField(name) })
        surname.addTextChangedListener(registerViewModel.createTextWatcher { registerViewModel.validateInputField(surname) })
        address.addTextChangedListener(registerViewModel.createTextWatcher { registerViewModel.validateAddress(address) })
        email.addTextChangedListener(registerViewModel.createTextWatcher { registerViewModel.validateEmail(email) })
        password.addTextChangedListener(registerViewModel.createTextWatcher { registerViewModel.validatePassword(password) })

        //Variabile utile per gestire la visualizzazione della password
        var isPasswordVisible = false

        binding.togglePasswordVisibilityReg.setOnClickListener {
            if (isPasswordVisible) {
                // Nascondi password
                binding.editTextPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                //Cambia l'icona della visualizzazione
                binding.togglePasswordVisibilityReg.setImageResource(R.mipmap.ic_visibility_off)
            } else {
                // Mostra password
                binding.editTextPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                //Cambia l'icona della visualizzazione
                binding.togglePasswordVisibilityReg.setImageResource(R.mipmap.ic_visibility)
            }
            // Sposta il cursore alla fine del testo
            binding.editTextPassword.setSelection(binding.editTextPassword.text.length)
            isPasswordVisible = !isPasswordVisible
        }

        binding.toggleConfPassVisibilityReg.setOnClickListener {
            if (isPasswordVisible) {
                // Nascondi password
                binding.editTextConfirmPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                //Cambia l'icona della visualizzazione
                binding.toggleConfPassVisibilityReg.setImageResource(R.mipmap.ic_visibility_off)
            } else {
                // Mostra password
                binding.editTextConfirmPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                //Cambia l'icona della visualizzazione
                binding.toggleConfPassVisibilityReg.setImageResource(R.mipmap.ic_visibility)
            }
            // Sposta il cursore alla fine del testo
            binding.editTextConfirmPassword.setSelection(binding.editTextConfirmPassword.text.length)
            isPasswordVisible = !isPasswordVisible
        }



        binding.buttonRegister.setOnClickListener{
            if (iT.isInternetAvailable(this)) {
                //Controlliamo i campi del form di registrazione
                val isFirstNameValid = registerViewModel.validateInputField(name)
                val isLastNameValid = registerViewModel.validateInputField(surname)
                val isAddressValid = registerViewModel.validateAddress(address)
                val isEmailValid = registerViewModel.validateEmail(email)
                val isPasswordValid = registerViewModel.validatePassword(password)
                val isConfirmPasswordValid = registerViewModel.validatePassword(confirmPassword)

                if(isFirstNameValid && isLastNameValid && isAddressValid && isEmailValid && isPasswordValid
                    && isConfirmPasswordValid){
                    if(password.text.toString() == confirmPassword.text.toString()){
                        if(binding.checkBox.isChecked){
                            registerViewModel.createAuthentication(email.text.toString(), password.text.toString())
                            registerViewModel.authenticatioSuccess.observe(this){ authenticatioSuccess ->
                                if(authenticatioSuccess) {
                                    registerViewModel.uid.observe(this) {
                                        registerViewModel.createUser(name.text.toString(), surname.text.toString(), address.text.toString(), photo)
                                        registerViewModel.userSuccess.observe(this){ userSuccess ->
                                            if(userSuccess){
                                                binding.editTextNome.text.clear()
                                                binding.editTextCognome.text.clear()
                                                binding.editTextIndirizzo.text.clear()
                                                binding.editTextEmail.text.clear()
                                                binding.editTextPassword.text.clear()
                                                binding.editTextConfirmPassword.text.clear()
                                                registerViewModel.createShoppingList()
                                                registerViewModel.shoppingListSuccess.observe(this){ shoppingListSuccess ->
                                                    if(shoppingListSuccess){
                                                        registerViewModel.createWallet()
                                                        registerViewModel.walletSuccess.observe(this){ walletSuccess ->
                                                            if(walletSuccess){
                                                                registerViewModel.createCoupons()
                                                                registerViewModel.couponsSuccess.observe(this){ couponsSuccess ->
                                                                    if(couponsSuccess){
                                                                        registerViewModel.createStats()
                                                                        registerViewModel.statsSuccess.observe(this) { statsSuccess ->
                                                                            if (statsSuccess) {
                                                                                Toast.makeText(this, "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show()
                                                                                val intent = Intent(this, LoginActivity::class.java)
                                                                                startActivity(intent)
                                                                            }else{
                                                                                Toast.makeText(this, "Errore durante la creazione delle statistiche", Toast.LENGTH_SHORT).show()
                                                                            }
                                                                        }
                                                                    }else{
                                                                        Toast.makeText(this, "Errore durante la creazione dei coupon", Toast.LENGTH_SHORT).show()
                                                                    }
                                                                }
                                                            }else{
                                                                Toast.makeText(this, "Errore durante la creazione della tessera a punti", Toast.LENGTH_SHORT).show()
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        Toast.makeText(this, "Errore durante la creazione della lista della spesa", Toast.LENGTH_SHORT).show()
                                                    }
                                                }


                                            }else{
                                                Toast.makeText(this, "Errore durante la registrazione", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    }
                                }else{
                                    Toast.makeText(this, "Email già associata ad un altro account utente", Toast.LENGTH_SHORT).show()
                                }
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
}