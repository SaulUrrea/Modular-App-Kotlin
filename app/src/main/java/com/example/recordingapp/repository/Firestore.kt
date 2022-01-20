package com.example.recordingapp.repository

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.recordingapp.MenuActivity
import com.example.recordingapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import org.json.JSONObject

class Firestore {

    var context: Context? = null
    var db: FirebaseFirestore? = null
    var Auth: FirebaseAuth? = null

    constructor(context: Context) {
        this.context = context
        loginSessionFir()
        initDb()
    }

    private fun initDb() {
        db = FirebaseFirestore.getInstance()
        try {
            val settings = FirebaseFirestoreSettings.Builder()
                .build()
            db!!.setFirestoreSettings(settings)
        } catch (e: Exception) {
        }
    }

    private fun loginSessionFir() {
        Auth = FirebaseAuth.getInstance()
        var user = Auth!!.currentUser
        if (user == null) {
            Auth!!.signInWithEmailAndPassword("it@coordinadora.com", "leqhddce")
                .addOnCompleteListener {
                    val user = Auth!!.currentUser
                }
        } else {
            user = Auth!!.currentUser
        }
    }
    fun validateUserDB(
        coding_employed: String,
        pin: String,
        context: Context,
        activity: Activity
    ) {
        db!!.collection("users")
            .whereEqualTo("codigo_empleado", coding_employed)
            .whereEqualTo("pin", pin)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) sendErrorMessage(context)
                val jsonObject = JSONObject()
                for (document in documents) {
                    if (!document.data.isNullOrEmpty()) {
                        Toast.makeText(context, R.string.toast_text, Toast.LENGTH_SHORT).show()
                        Thread.sleep(3000)
                        val intent = Intent(activity, MenuActivity::class.java).apply {
                            putExtra("user", document.getString("name"))
                        }
                        activity.startActivity(intent)
                        activity.finish()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Fallo al ingresar", "Error getting documents.", exception)
                sendErrorMessage(context)
            }
    }

    private fun sendErrorMessage(context: Context) {
        Toast.makeText(context, R.string.toast_text_error, Toast.LENGTH_SHORT).show()
    }

    fun actualizarUsuario(coding_employed: String, context: Context) {
        db!!.collection("users")
            .whereEqualTo("codigo_empleado", coding_employed)
            .get()
            .addOnSuccessListener {
                Toast.makeText(context, "Adamos por el actualizar exitoso", Toast.LENGTH_SHORT).show()
            }
    }

    fun returnDb(): FirebaseFirestore {
        return db!!
    }
}