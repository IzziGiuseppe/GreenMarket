package com.example.greenmarket.ui.login.profilo_utente

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class UserProfileViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val storageRef = FirebaseStorage.getInstance().reference
    private val currentUser = FirebaseAuth.getInstance().currentUser

    private val _nomeUtente = MutableLiveData<String>()
    val nomeUtente: LiveData<String> get() = _nomeUtente

    private val _cognomeUtente = MutableLiveData<String>()
    val cognomeUtente: LiveData<String> get() = _cognomeUtente

    private val _indirizzoUtente = MutableLiveData<String>()
    val indirizzoUtente: LiveData<String> get() = _indirizzoUtente

    private val _fotoUtente = MutableLiveData<String>()
    val fotoUtente: LiveData<String> get() = _fotoUtente

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

    private val _deleteAccountSuccess = MutableLiveData<Boolean>()
    val deleteAccountSuccess: LiveData<Boolean> get() = _deleteAccountSuccess

    val urlFotoUtente: MutableLiveData<String> = MutableLiveData()

    var originalData: Map<String, Any>? = null

    fun loadUserData() {
        currentUser?.let { user ->
            val docRef = db.collection("users").document(user.uid)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        _nomeUtente.value = document.getString("nome")
                        _cognomeUtente.value = document.getString("cognome")
                        _indirizzoUtente.value = document.getString("indirizzo")
                        _fotoUtente.value = document.getString("foto")

                        // Salva i dati originali
                        originalData = document.data

                        _loadUserDataSuccess.value = true
                    } else {
                        _loadUserDataSuccess.value = false
                    }
                }
                .addOnFailureListener {
                    _loadUserDataSuccess.value = false
                }
        }
    }

    fun saveData(name: String, surname: String, address: String, uri: Uri?, contentResolver: ContentResolver) {
        currentUser?.let { user ->
            val updates = mutableMapOf<String, Any>()
            updates["nome"] = name
            updates["cognome"] = surname
            updates["indirizzo"] = address

            db.collection("users").document(user.uid).update(updates)
                .addOnSuccessListener {
                    _uploadUserDataSuccess.value = true
                    if (uri != null) {
                        uploadImage(uri, contentResolver)
                    }
                }
                .addOnFailureListener {
                    _uploadUserDataSuccess.value = false
                }
        }
    }

    private fun uploadImage(uri: Uri, contentResolver: ContentResolver) {
        currentUser?.let { user ->
            val photoRef = storageRef.child("images/${user.uid}.jpg")
            val uploadTask = photoRef.putFile(uri)

            uploadTask.addOnSuccessListener {
                _putFileSuccess.value = true
                photoRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                    _downloadUrlSuccess.value = true
                    urlFotoUtente.value = downloadUrl.toString()
                    db.collection("users").document(user.uid).update("foto", downloadUrl.toString())
                        .addOnSuccessListener {
                            _updatePhotoSuccess.value = true
                        }
                        .addOnFailureListener {
                            _updatePhotoSuccess.value = false
                        }
                }.addOnFailureListener {
                    _downloadUrlSuccess.value = false
                }
            }.addOnFailureListener {
                _putFileSuccess.value = false
            }
        }
    }

    fun deleteAccount() {
        currentUser?.let { user ->
            db.collection("users").document(user.uid).delete()
                .addOnSuccessListener {
                    user.delete()
                        .addOnSuccessListener {
                            _deleteAccountSuccess.value = true
                        }
                        .addOnFailureListener {
                            _deleteAccountSuccess.value = false
                        }
                }
                .addOnFailureListener {
                    _deleteAccountSuccess.value = false
                }
        }
    }
}
