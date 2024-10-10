package com.example.appformas

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Triangulo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_triangulo)

        val txtResTriangulo = findViewById<TextView>(R.id.txtResultadoTriangulo)

        val bundle:Bundle ?= intent.extras
        val base:Double = bundle!!.getDouble("baseTriangulo")
        val altura:Double = bundle!!.getDouble("alturaTriangulo")

        val triangulo:Double = (base * altura) /2

        txtResTriangulo.text=triangulo.toString()
    }
}