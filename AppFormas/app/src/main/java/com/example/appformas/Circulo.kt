package com.example.appformas

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Circulo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_circulo)

        val txtResCirculo = findViewById<TextView>(R.id.txtResultadoCirculo)

        val bundle:Bundle ?= intent.extras
        val radio:Double = bundle!!.getDouble("radioCirculo")

        val Circulo:Double = Math.PI * (radio *radio)

        txtResCirculo.text=Circulo.toString()
    }
}