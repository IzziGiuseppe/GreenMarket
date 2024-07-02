package com.example.greenmarket.ui.login.profilo_utente

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.greenmarket.ui.internet.InternetTest
import com.example.greenmarket.databinding.ActivityProfiloUtenteBinding
import com.example.greenmarket.ui.home.HomeViewModel
import com.example.greenmarket.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage


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
                Log.d("Acitvity", "AVVIO METODO")
                showDeleteAccountConfirmationDialog()
            }else{
                iT.toast(this)
            }
        }
    }

    //Funzione per ripristinare i dati nel db Firestore
    private fun cancelChanges(originalData: Map<String, Any>?) {
        originalData?.let {
            binding.editTextNomeUP.setText(it["nome"] as String)
            binding.editTextCognomeUP.setText(it["cognome"] as String)
            binding.editTextIndirizzoUP.setText(it["indirizzo"] as String)
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
        Log.d("Acitvity", "DENTRO IL METODO")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Conferma Eliminazione Account")
        builder.setMessage("Sei sicuro di voler eliminare l'account?")

        builder.setPositiveButton("Sì") { _, _ ->
            Log.d("Acitvity", "APPENA ENTRATI NEL METODO")

            userProfileViewModel.deleteAccount()
            userProfileViewModel.deleteAccountSuccess.observe(this) { deleteAccountSuccess ->
                if (deleteAccountSuccess) {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Account eliminato con successo", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Errore nell'eliminazione dell'account", Toast.LENGTH_SHORT).show()
                }
            }
        }

            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
}