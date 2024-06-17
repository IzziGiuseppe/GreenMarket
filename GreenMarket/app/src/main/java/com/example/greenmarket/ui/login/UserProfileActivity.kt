package com.example.greenmarket.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.greenmarket.databinding.ActivityProfiloUtenteBinding
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inizializzazione del binding
        binding = ActivityProfiloUtenteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inizializzazione db Firestore
        db = FirebaseFirestore.getInstance()

        //Inizializzazione storage Firebase
        storageRef = FirebaseStorage.getInstance().reference.child("images")

        //Accesso alla galleria
        val galleryImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                if (it != null) {
                    binding.imageViewProfile.setImageURI(it)
                    uri = it
                }
            }
        )

        // Ottenere l'ID dell'utente corrente
        if (currentUser != null) {
            val userID = currentUser.uid
            Log.d("UserProfileActivity", "User ID: $userID")  // Log l'ID dell'utente corrente

            db.collection("users").document(userID).get()
                .addOnSuccessListener {
                    if (it != null && it.exists()) {
                        val name = it.getString("nome")
                        val surname = it.getString("cognome")
                        val address = it.getString("indirizzo")
                        val photo = it.getString("foto")

                        // Aggiorna le EditText con i dati recuperati
                        binding.editTextNomeUP.setText(name)
                        binding.editTextCognomeUP.setText(surname)
                        binding.editTextIndirizzoUP.setText(address)
                        if (!photo.isNullOrEmpty()) {
                            Glide.with(this)
                                .load(photo)
                                .into(binding.imageViewProfile)
                        } else {
                            Log.d("UserProfileActivity", "Nessuna immagine trovata per l'utente.")
                        }
                        originalData = it.data
                    } else {
                        // Documento non trovato
                        Log.w("UserProfileActivity", "Documento non trovato")
                        Toast.makeText(this, "Dati non visualizzabili", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    // Gestisci l'errore
                    Log.e("UserProfileActivity", "Errore nel caricamento dei dati")
                    Toast.makeText(this, "Errore nel caricamneto dei dati", Toast.LENGTH_SHORT).show()
                }
        } else {
            // Utente non loggato
            Log.w("UserProfileActivity", "Utente non loggato")
            Toast.makeText(this, "Utente non loggato", Toast.LENGTH_SHORT).show()
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
            saveData()
        }

        //Ripristina i dati originali nel db Firestore
        binding.buttonCancel.setOnClickListener {
            cancelChanges(originalData)
        }

        //Elimina un'account utente dal db Firestore
        binding.textViewEliminaAccount.setOnClickListener{
            showDeleteAccountConfirmationDialog()
        }
    }


    //Funzione per aggiornare i dati nel db Firestore
    private fun saveData() {
        val nome = binding.editTextNomeUP.text.toString().trim()
        val cognome = binding.editTextCognomeUP.text.toString().trim()
        val indirizzo = binding.editTextIndirizzoUP.text.toString().trim()

        currentUser?.let { user ->
            val updates = mutableMapOf<String, Any>()
            updates.clear()
            originalData?.let { original ->
                if (original["nome"] != nome && nome.isNotEmpty()) {
                    updates["nome"] = nome
                } else if (nome.isEmpty()) {
                    Toast.makeText(this, "Inserisci il tuo nome", Toast.LENGTH_SHORT).show()
                    return
                }

                if (original["cognome"] != cognome && cognome.isNotEmpty()) {
                    updates["cognome"] = cognome
                } else if (cognome.isEmpty()) {
                    Toast.makeText(this, "Inserisci il tuo cognome", Toast.LENGTH_SHORT).show()
                    return
                }

                if (original["indirizzo"] != indirizzo && indirizzo.isNotEmpty()) {
                    updates["indirizzo"] = indirizzo
                } else if (indirizzo.isEmpty()) {
                    Toast.makeText(this, "Inserisci il tuo indirizzo", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            //COMPAIONO DEI MESSAGGI IN MODO NON COERENTE
            Log.d("UserProfileActivity", "Aggiornamenti: $updates")


            if (updates.isNotEmpty()) {
                db.collection("users").document(user.uid).update(updates)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Dati aggiornati con successo", Toast.LENGTH_SHORT).show()
                        updates.clear()
                        Log.d("UserProfileActivity", "Aggiornamenti: $updates")
                    }
                    .addOnFailureListener { e ->
                        Log.e("UserProfileActivity", "Errore nell'aggiornamento dei dati", e)
                        Toast.makeText(this, "Errore nell'aggiornamento dei dati", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Nessun dato anagrafico da aggiornare", Toast.LENGTH_SHORT).show()
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

}