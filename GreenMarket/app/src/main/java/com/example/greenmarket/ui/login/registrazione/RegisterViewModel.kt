package com.example.greenmarket.ui.login.registrazione

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greenmarket.db.model.ProdottoInStatsModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private var firebaseAuth = FirebaseAuth.getInstance()
    private var db = FirebaseFirestore.getInstance()

    private val _statsSuccess = MutableLiveData<Boolean>()
    val statsSuccess: LiveData<Boolean> get() = _statsSuccess

    private val _couponsSuccess = MutableLiveData<Boolean>()
    val couponsSuccess: LiveData<Boolean> get() = _couponsSuccess

    private val _walletSuccess = MutableLiveData<Boolean>()
    val walletSuccess: LiveData<Boolean> get() = _walletSuccess

    private val _shoppingListSuccess = MutableLiveData<Boolean>()
    val shoppingListSuccess: LiveData<Boolean> get() = _shoppingListSuccess

    private val _userSuccess = MutableLiveData<Boolean>()
    val userSuccess: LiveData<Boolean> get() = _userSuccess

    private val _authenticatioSuccess = MutableLiveData<Boolean>()
    val authenticatioSuccess: LiveData<Boolean> get() = _authenticatioSuccess

    private val _uid = MutableLiveData<String>()
    val uid: MutableLiveData<String> get() = _uid

    //Funzione che crea l'user id mediante email e password
    fun createAuthentication(email: String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                _uid.value = firebaseAuth.currentUser?.uid
                _authenticatioSuccess.value = true
            }
            .addOnFailureListener{
                _authenticatioSuccess.value = false
            }
    }

    //Funzione che crea l'utente
    fun createUser(name: String, surname: String, address: String, photo: String){
        val userMap = hashMapOf(
            "nome" to name,
            "cognome" to surname,
            "indirizzo" to address,
            "foto" to photo
        )

        db.collection("users").document(uid.value.toString()).set(userMap)
            .addOnSuccessListener {
                _userSuccess.value = true
            }
            .addOnFailureListener{
                _userSuccess.value = false
            }
    }

    //Funzione che crea la lista della spesa
    fun createShoppingList(){
        val prodotti: Map<String?, List<Float>?> = emptyMap()
        //Creazione lista della spesa associata all'utente
        val listaSpesa = hashMapOf(
            "data" to null,
            "valido" to false,
            "prodotti" to prodotti
        )

        db.collection("users").document(uid.value.toString()).collection("historical").document("shoppingList").set(listaSpesa)
            .addOnSuccessListener{
                _shoppingListSuccess.value = true
            }
            .addOnFailureListener{
                _shoppingListSuccess.value = false
            }
    }

    //Funzione che crea il wallwt
    fun createWallet(){
        val tesseraPunti = hashMapOf(
            "saldo" to 0f,
            "punti" to 0
        )

        db.collection("users").document(uid.value.toString()).collection("pointCard").document("wallet").set(tesseraPunti)
            .addOnSuccessListener{
                _walletSuccess.value = true
            }
            .addOnFailureListener{
                _walletSuccess.value = false
            }
    }

    //Funzione che crea i coupon
    fun createCoupons(){
        val coupons = mutableListOf<String>()
        val setCS = hashMapOf(
            "codici sconto" to coupons
        )

        db.collection("users").document(uid.value.toString()).collection("pointCard").document("coupons").set(setCS)
            .addOnSuccessListener{
                _couponsSuccess.value = true
            }
            .addOnFailureListener{
                _couponsSuccess.value = false
            }

    }

    //Funzione che gestisce la creazione delle statistiche
    fun createStats() {
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
        val peperoni = ProdottoInStatsModel("Peperoni", 0f)
        val fragole = ProdottoInStatsModel("Fragole", 0f)
        val cicorie = ProdottoInStatsModel("Cicorie", 0f)

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
            "Patate" to patate.quantitaTot,
            "Peperoni" to peperoni.quantitaTot,
            "Fragole" to fragole.quantitaTot,
            "Cicorie" to cicorie.quantitaTot
        )

        db.collection("users").document(uid.value.toString()).collection("stats").document("top_selling_products").set(prodottiStats)
            .addOnSuccessListener {
                _statsSuccess.value = true
            }
            .addOnFailureListener {
                _statsSuccess.value = false
            }

    }

    //Crea un TextWatcher generico che accetta una funzione di validazione e la esegue ogni volta che il testo cambia.
    fun createTextWatcher(validationFunction: () -> Boolean): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validationFunction()
            }
        }
    }

    //Funzione che verifica se il nome inserito è valido
    fun validateInputField(editText: EditText): Boolean {
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
    fun validateAddress(editText: EditText): Boolean {
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
    fun validateEmail(editText: EditText): Boolean {
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
    fun validatePassword(editText: EditText): Boolean {
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