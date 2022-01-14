package com.example.recordingapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.recordingapp.repository.Firestore
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var firestore: Firestore

    private lateinit var inputPws: EditText
    private lateinit var inputUser: EditText
    private lateinit var btnInto: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (this.application as ApplicationData).getApplicationComponent().inject(this)
        initVars()
        validateButton()
        btnInto.setOnClickListener {
            firestore.validateUserDB(
                inputUser.text.toString().trim(),
                inputPws.text.toString().trim(),
                applicationContext,
                this@MainActivity
            )

        }
    }

    private fun initVars() {
        inputUser = findViewById(R.id.etUser)
        inputPws = findViewById(R.id.etPassword)
        btnInto = findViewById(R.id.btnInto)
    }

    private fun validateButton() {
        inputPws.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("beforeTextChanged", "not override")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (inputPws.length() > 1 && inputUser.length() > 1){
                    btnInto.isEnabled = true
                    btnInto.setTextColor(Color.parseColor("#FFFFFF"));
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i("afterTextChanged", "not override")
            }

        })
    }

}