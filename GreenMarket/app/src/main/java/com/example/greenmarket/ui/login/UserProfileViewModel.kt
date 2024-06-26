package com.example.greenmarket.ui.login

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import java.security.MessageDigest

class UserProfileViewModel(application: Application) : AndroidViewModel(application) {

    private var db = FirebaseFirestore.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private var originalData: Map<String, Any>? = null
    private var storageRef = FirebaseStorage.getInstance().reference.child("images")
    private var uri: Uri? = null

    private val _loadUserDataSuccess = MutableLiveData<Boolean>()
    val loadUserDataSuccess: LiveData<Boolean> get() = _loadUserDataSuccess

    private val _uploadUserDataSuccess = MutableLiveData<Boolean>()
    val uploadUserDataSuccess: LiveData<Boolean> get() = _uploadUserDataSuccess

    private val _putFileSuccess = MutableLiveData<Boolean>()
    val putFileSuccess: LiveData<Boolean> get() = _putFileSuccess

    private val _downloadUrlSuccess = MutableLiveData<Boolean>()
    val downloadUrlSuccess: LiveData<Boolean> get() = _downloadUrlSuccess

    private val _updatePhotoSuccess = MutableLiveData<Boolean>()
    val updatePhotoSuccess: LiveData<Boolean> get() = _updatePhotoSuccess

    private val _nomeUtente = MutableLiveData<String>()
    val nomeUtente: LiveData<String> get() = _nomeUtente

    private val _cognomeUtente = MutableLiveData<String>()
    val cognomeUtente: LiveData<String> get() = _cognomeUtente

    private val _indirizzoUtente = MutableLiveData<String>()
    val indirizzoUtente: LiveData<String> get() = _indirizzoUtente

    private val _fotoUtente = MutableLiveData<String>()
    val fotoUtente: LiveData<String> get() = _fotoUtente

    private val _urlFotoUtente = MutableLiveData<String>()
    val urlFotoUtente: LiveData<String> get() = _urlFotoUtente

    //Funzione che carica i dati dell'utente dal database
    fun loadUserData() {
        currentUser?.let {
            db.collection("users").document(it.uid).get()
                .addOnSuccessListener {document->
                    _nomeUtente.value = document.getString("nome")
                    _cognomeUtente.value = document.getString("cognome")
                    _indirizzoUtente.value = document.getString("indirizzo")
                    _fotoUtente.value = document.getString("foto")

                    originalData = document.data
                    _loadUserDataSuccess.value = true
                }
                .addOnFailureListener {
                    _loadUserDataSuccess.value = false
                }
        }
    }

    //Funzione per aggiornare i dati nel db Firestore
    fun saveData(name: EditText, surname: EditText, address: EditText, uri: Uri?, contentResolver: ContentResolver) {
        name.addTextChangedListener(createTextWatcher { validateInputField(name) })
        surname.addTextChangedListener(createTextWatcher { validateInputField(surname) })
        address.addTextChangedListener(createTextWatcher { validateAddress(address) })

        val isFirstNameValid = validateInputField(name)
        val isLastNameValid = validateInputField(surname)
        val isAddressValid = validateAddress(address)

        currentUser?.let { user ->
            val updates = mutableMapOf<String, Any>()
            updates.clear()
            originalData?.let { original ->
                if (original["nome"] != name.text.toString() && isFirstNameValid) {
                    updates["nome"] = name.text.toString()
                }
                if (original["cognome"] != surname.text.toString() && isLastNameValid) {
                    updates["cognome"] = surname.text.toString()
                }
                if (original["indirizzo"] != address.text.toString() && isAddressValid) {
                    updates["indirizzo"] = address.text.toString()
                }
            }

            originalData?.let { original ->
                if ((original["nome"] != name.text.toString() && isFirstNameValid) || (original["cognome"] != surname.text.toString() && isLastNameValid) || (original["indirizzo"] != address.text.toString() && isAddressValid)) {
                    db.collection("users").document(user.uid).update(updates)
                        .addOnSuccessListener {
                            db.collection("users").document(user.uid).get()
                                .addOnSuccessListener {document->
                                    originalData = document.data
                                }
                            _uploadUserDataSuccess.value = true
                        }
                        .addOnFailureListener {
                            _uploadUserDataSuccess.value = false
                        }
                } else {
                    Toast.makeText(getApplication(), "Dati anagrafici non aggiornabili", Toast.LENGTH_SHORT).show()
                }
            }

            uri?.let { imageUri ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val newImageStream = contentResolver.openInputStream(imageUri)
                        val newImageHash = newImageStream?.use { stream -> getHash(stream.readBytes()) }

                        if (newImageHash == null) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(getApplication(), "Errore nel calcolo dell'hash della nuova immagine", Toast.LENGTH_SHORT).show()
                            }
                            return@launch
                        }

                        db.collection("users").document(user.uid).get()
                            .addOnSuccessListener { document ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    val currentImageUrl = document.getString("foto")
                                    if (currentImageUrl != null) {
                                        val currentImageHash = URL(currentImageUrl).openStream().use { inputStream ->
                                            getHash(inputStream.readBytes())
                                        }
                                        if (newImageHash != currentImageHash) {
                                            withContext(Dispatchers.Main) {
                                                uploadNewImage(imageUri, user, storageRef, db)
                                            }
                                        } else {
                                            withContext(Dispatchers.Main) {
                                                Toast.makeText(getApplication(), "L'immagine è già presente nel database", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    } else {
                                        withContext(Dispatchers.Main) {
                                            uploadNewImage(imageUri, user, storageRef, db)
                                        }
                                    }
                                }
                            }
                            .addOnFailureListener {
                                CoroutineScope(Dispatchers.Main).launch {
                                    Toast.makeText(getApplication(), "Errore nel recupero dell'immagine corrente", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Log.e("UserProfileActivity", "Errore durante il calcolo dell'hash dell'immagine", e)
                            Toast.makeText(getApplication(), "Errore durante il calcolo dell'hash dell'immagine", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    //Funzione per gestire l'inserimento della nuova immagine
    private fun uploadNewImage(uri: Uri, user: FirebaseUser, storageRef: StorageReference, db: FirebaseFirestore) {
        val userImageRef = storageRef.child("users/${user.uid}.jpg")
        userImageRef.putFile(uri)
            .addOnSuccessListener {
                _putFileSuccess.value = true
                userImageRef.downloadUrl
                    .addOnSuccessListener { url ->
                        _downloadUrlSuccess.value = true
                        db.collection("users").document(user.uid).update("foto", url.toString())
                            .addOnSuccessListener {
                                _updatePhotoSuccess.value = true
                                _urlFotoUtente.value = url.toString()
                            }
                            .addOnFailureListener {
                                _updatePhotoSuccess.value = false
                            }
                    }
                    .addOnFailureListener {
                        _downloadUrlSuccess.value = false
                    }
            }
            .addOnFailureListener {
                _putFileSuccess.value = false
            }
    }

    //Funzione che calcola l'hash di un'array di byte
    private fun getHash(bytes: ByteArray): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(bytes)
        return hashBytes.joinToString("") { "%02x".format(it) }
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
}