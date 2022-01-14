package com.medicina.dinamico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.recordingapp.ApplicationData
import com.example.recordingapp.repository.Firestore
import com.google.firebase.firestore.EventListener
import com.medicina.dinamico.Dagger.DaggerPerfilComponent
import com.medicina.dinamico.Dagger.PerfilModule
import org.w3c.dom.Text
import javax.inject.Inject

class PerfilAct : AppCompatActivity() {

    private lateinit var user: String
    private lateinit var password: String
    private lateinit var tvNameUser: EditText
    private lateinit var tvEmail: EditText
    private lateinit var btnActualiza: Button

    @Inject
    lateinit var firestore: Firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dinamico)

        DaggerPerfilComponent.builder().applicationComponent((this.application as ApplicationData)
            .getApplicationComponent()).perfilModule(PerfilModule(this)).build().inject(this)

        val bundle: Bundle? = intent.extras
        user = bundle?.getString("user").toString()
        password = bundle?.getString("password").toString()

        tvNameUser = findViewById(R.id.nombreUsuario)
        tvEmail = findViewById(R.id.correoElectronico)
        btnActualiza = findViewById(R.id.btnInto)

        firestore.returnDb().collection("users")
            .whereEqualTo("codigo_empleado", user)
            .addSnapshotListener { value, error ->
                for (document in value!!.documents) {
                    tvNameUser.setText("")
                    tvEmail.setText("")

                    tvNameUser.setText(document.getString("name"))
                    tvEmail.setText(document.getString("email"))
                }
            }

        btnActualiza.setOnClickListener {
            firestore.actualizarUsuario(tvNameUser.text.toString(), this)
        }

    }
}