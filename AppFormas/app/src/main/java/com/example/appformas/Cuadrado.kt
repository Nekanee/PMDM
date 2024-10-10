package com.example.appformas

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.time.times

class Cuadrado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cuadrado)

        val txtResCuadrado = findViewById<TextView>(R.id.txtResultadoCuadrado)

        val bundle:Bundle ?= intent.extras
        val lado:Double = bundle!!.getDouble("ladoCuadrado")

        val periCuadrado:Double = lado * lado

        txtResCuadrado.text=periCuadrado.toString()




    }
}