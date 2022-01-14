package com.example.task

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity: AppCompatActivity(), OnMapReadyCallback {

    private lateinit var tvReceiver: TextView
    private lateinit var tvPhoneNumber: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvCity: TextView
    private lateinit var btnSuccess: Button
    private lateinit var map: GoogleMap
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        createFragment()
        initVars()
        validateTask()
        setSupportActionBar(toolbar)

        findViewById<FloatingActionButton>(R.id.fbBack).setOnClickListener {
            onBackPressed()
            finish()
        }

    }

    private fun validateTask() {
        if (intent.extras!!.getString("recogida") == null){
            toolbar.setTitle("Guia: " + intent.extras!!.getString("guia").toString());
            tvReceiver.text = intent.extras!!.getString("receiver").toString()
            tvPhoneNumber.text = intent.extras!!.getString("phone").toString()
            tvAddress.text = intent.extras!!.getString("address").toString()
            tvCity.text = intent.extras!!.getString("city").toString()
        } else {
            toolbar.setTitle("Recogida: " + intent.extras!!.getString("recogida").toString());
            tvReceiver.text = intent.extras!!.getString("claimant").toString()
            tvPhoneNumber.text = intent.extras!!.getString("phone").toString()
            tvAddress.text = intent.extras!!.getString("address").toString()
            tvCity.text = intent.extras!!.getString("city").toString()
            btnSuccess.text = "iniciar recogida"
        }
    }

    private fun initVars() {
        toolbar = findViewById(R.id.toolbar)
        tvReceiver = findViewById<TextView>(R.id.tvReceiver)
        tvPhoneNumber = findViewById<TextView>(R.id.tvPhoneNumber)
        tvAddress = findViewById<TextView>(R.id.tvDir)
        tvCity = findViewById<TextView>(R.id.tvCity)
        btnSuccess = findViewById<Button>(R.id.btnSucces)
    }

    private fun createFragment() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
    }

    private fun createMarker() {
        var lat = intent.extras!!.getDouble("lat")
        var long = intent.extras!!.getDouble("long")
        var coordinates = LatLng(lat,long)
        var market = MarkerOptions().position(coordinates).title("Nueva tarea")
        map.addMarker(market)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates,18f),
            3000,
            null
        )
    }
}