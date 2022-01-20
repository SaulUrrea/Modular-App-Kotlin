package com.example.recordingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val bundle: Bundle? = intent.extras
        val user = bundle?.getString("user").toString()
        findViewById<Button>(R.id.btnList).setOnClickListener {
            var intent = Intent()
            intent.setClassName(packageName,"com.example.task.TasksActivity").apply {
                putExtra("user",user)
            }
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnProfiler).setOnClickListener {
            var intent = Intent()
            intent.setClassName(packageName, "com.medicina.dinamico.PerfilAct").apply {
                putExtra("user", user)
            }
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnExit).setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}