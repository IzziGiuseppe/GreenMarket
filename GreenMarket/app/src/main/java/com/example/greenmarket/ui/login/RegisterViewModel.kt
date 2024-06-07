package com.example.greenmarket.ui.login

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun registerListener(email: String, password: String, name: String, surname: String, address: String, photo: String) : Boolean{
        var flag = false
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
                            flag = true
                        }
                        .addOnFailureListener{
                            flag = false
                        }
                }
            }
        return false
    }
}