package com.example.greenmarket.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.greenmarket.InternetTest
import com.example.greenmarket.databinding.ActivityProfiloUtenteBinding
import com.example.greenmarket.ui.home.HomeViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.net.URL
import java.security.MessageDigest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfiloUtenteBinding
    private lateinit var db: FirebaseFirestore
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private var originalData: Map<String, Any>? = null
    private var storageRef = FirebaseStorage.getInstance().reference
    private var uri: Uri? = null
    private val userProfileViewModel: UserProfileViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val iT = InternetTest()

        //Inizializzazione del binding
        binding = ActivityProfiloUtenteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = binding.editTextNomeUP
        val surname = binding.editTextCognomeUP
        val address = binding.editTextIndirizzoUP
        val photo = binding.imageViewProfile




        //Inizializzazione db Firestore
        db = FirebaseFirestore.getInstance()

        //Inizializzazione storage Firebase
        storageRef = FirebaseStorage.getInstance().reference.child("images")

        //Accesso alla galleria
        val galleryImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                if (it != null) {
                    photo.setImageURI(it)
                    uri = it
                }
            }
        )

        //Recupera i dati dal db Firestore
        userProfileViewModel.loadUserData()
        userProfileViewModel.nomeUtente.observe(this) {nome->
            name.setText(nome)
        }
        userProfileViewModel.cognomeUtente.observe(this) {cognome->
            surname.setText(cognome)
        }
        userProfileViewModel.indirizzoUtente.observe(this) {indirizzo->
            address.setText(indirizzo)
        }
        userProfileViewModel.fotoUtente.observe(this) {foto->
            if (!foto.isNullOrEmpty()) {
                Glide.with(this)
                    .load(foto)
                    .into(photo)
            } else {
                Toast.makeText(this, "Nessuna immagine trovata per l'utente", Toast.LENGTH_SHORT).show()
            }
        }

        userProfileViewModel.loadUserDataSuccess.observe(this){loadSuccess->
            if (!loadSuccess){
                Toast.makeText(this, "Errore nel caricamneto dei dati", Toast.LENGTH_SHORT).show()
            }
        }

        // Listener per la scelta dell'immagine
        binding.floatingActionButtonModFoto.setOnClickListener {
            galleryImage.launch("image/*")
        }

        // Listener per il logout
        binding.textViewLogout.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        //Modifica i dati nel db Firestore
        binding.buttonSave.setOnClickListener {
            if (iT.isInternetAvailable(this)) {
                userProfileViewModel.saveData(name, surname, address, uri, contentResolver)
                userProfileViewModel.uploadUserDataSuccess.observe(this){uploadSuccess->
                    if(uploadSuccess){
                        homeViewModel.setNome(name.text.toString())
                        //Poco chiaro perchè venga chiamato più volte il Toast
                        Toast.makeText(this, "Dati anagrafici aggiornati con successo", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Errore nell'aggiornamento dei dati anagrafici", Toast.LENGTH_SHORT).show()
                    }
                }
                userProfileViewModel.putFileSuccess.observe(this){putFileSuccess->
                    if(putFileSuccess){
                        userProfileViewModel.downloadUrlSuccess.observe(this){downloadUrlSuccess->
                            if(downloadUrlSuccess){
                                userProfileViewModel.updatePhotoSuccess.observe(this) { updatePhotoSuccess ->
                                    if (updatePhotoSuccess) {
                                        homeViewModel.setFoto(userProfileViewModel.urlFotoUtente.value.toString())
                                        Toast.makeText(this, "Foto caricata con successo", Toast.LENGTH_SHORT).show()
                                    }else{
                                        Toast.makeText(this, "Errore nel salvataggio della foto", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }else{
                                Toast.makeText(this, "Errore nel recupero dell'URL della foto", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{
                        Toast.makeText(this, "Errore nel caricamento della foto", Toast.LENGTH_SHORT).show()
                    }
                }

            }else{
                iT.toast(this)
            }
        }

        //Ripristina i dati originali nel db Firestore
        binding.buttonCancel.setOnClickListener {
            if (iT.isInternetAvailable(this)) {
                cancelChanges(originalData)
            }else{
                iT.toast(this)
            }
        }

        //Elimina un'account utente dal db Firestore
        binding.textViewEliminaAccount.setOnClickListener{
            if (iT.isInternetAvailable(this)) {
                showDeleteAccountConfirmationDialog()
            }else{
                iT.toast(this)
            }
        }
    }


    //Funzione per aggiornare i dati nel db Firestore
    private fun saveData(name: EditText, surname: EditText, address: EditText) {
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
                if (original["nome"] != name.text.toString() || original["cognome"] != surname.text.toString() || original["indirizzo"] != address.text.toString()) {
                    db.collection("users").document(user.uid).update(updates)
                        .addOnSuccessListener {
                            db.collection("users").document(user.uid).get()
                                .addOnSuccessListener {document->
                                    originalData = document.data
                                }
                            homeViewModel.setNome(name.text.toString())
                            Toast.makeText(this, "Dati anagrafici aggiornati con successo", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Errore nell'aggiornamento dei dati anagrafici", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this, "Nessun dato anagrafico da aggiornare", Toast.LENGTH_SHORT).show()
                }
            }

            uri?.let { imageUri ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val newImageStream = contentResolver.openInputStream(imageUri)
                        val newImageHash = newImageStream?.use { stream -> getHash(stream.readBytes()) }

                        if (newImageHash == null) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(this@UserProfileActivity, "Errore nel calcolo dell'hash della nuova immagine", Toast.LENGTH_SHORT).show()
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
                                                Toast.makeText(this@UserProfileActivity, "L'immagine è già presente nel database", Toast.LENGTH_SHORT).show()
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
                                    Toast.makeText(this@UserProfileActivity, "Errore nel recupero dell'immagine corrente", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Log.e("UserProfileActivity", "Errore durante il calcolo dell'hash dell'immagine", e)
                            Toast.makeText(this@UserProfileActivity, "Errore durante il calcolo dell'hash dell'immagine", Toast.LENGTH_SHORT).show()
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
                userImageRef.downloadUrl
                    .addOnSuccessListener { url ->
                        db.collection("users").document(user.uid).update("foto", url.toString())
                            .addOnSuccessListener {
                                homeViewModel.setFoto(url.toString())
                                Toast.makeText(this, "Foto caricata con successo", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener { e ->
                                Log.e("UserProfileActivity", "Errore nel salvataggio della foto", e)
                                Toast.makeText(this, "Errore nel salvataggio della foto", Toast.LENGTH_SHORT).show()
                            }
                    }
                    .addOnFailureListener { e ->
                        Log.e("UserProfileActivity", "Errore nel recupero dell'URL della foto", e)
                        Toast.makeText(this, "Errore nel recupero dell'URL della foto", Toast.LENGTH_SHORT).show()
                    }
            }
            .addOnFailureListener { e ->
                Log.e("UserProfileActivity", "Errore nel caricamento della foto", e)
                Toast.makeText(this, "Errore nel caricamento della foto", Toast.LENGTH_SHORT).show()
            }
    }

    //Funzione che calcola l'hash di un'array di byte
    private fun getHash(bytes: ByteArray): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(bytes)
        return hashBytes.joinToString("") { "%02x".format(it) }
    }

    //Funzione per ripristinare i dati nel db Firestore
    private fun cancelChanges(originalData: Map<String, Any>?) {
        originalData?.let {
            binding.editTextNomeUP.setText(it["nome"] as String)
            binding.editTextCognomeUP.setText(it["cognome"] as String)
            binding.editTextIndirizzoUP.setText(it["indirizzo"] as String)
        }
    }

    //Funzione per eliminare un'account utente dal db Firestore
    private fun deleteAccount(){
        currentUser?.let {it ->
            db.collection("users").document(it.uid).delete()
                .addOnSuccessListener {userData ->
                    it.delete()
                        .addOnSuccessListener {
                            Toast.makeText(this, "Utente eliminato con successo", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Errore durante l'eliminazione dell'utente", Toast.LENGTH_SHORT).show()
                        }
                }
        }
    }

    //Funzione che mostra un messaggio di alert in fase di logout
    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Conferma Logout")
        builder.setMessage("Sei sicuro di voler uscire dall'account?")

        builder.setPositiveButton("Sì") { _, _ ->
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Funzione che mostra un messaggio di alert in fase di eliminazione dell'account
    private fun showDeleteAccountConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Conferma Eliminazione Account")
        builder.setMessage("Sei sicuro di voler eliminare l'account?")

        builder.setPositiveButton("Sì") { _, _ ->
            deleteAccount()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

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
}