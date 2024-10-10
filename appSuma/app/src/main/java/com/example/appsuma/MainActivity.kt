package com.example.appsuma

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edtN1 = findViewById<EditText>(R.id.edtN1)
        val edtN2 = findViewById<EditText>(R.id.edtN2)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val btnResta = findViewById<Button>(R.id.btnResta)
        val btnMulti = findViewById<Button>(R.id.btnMulti)
        val btnDiv = findViewById<Button>(R.id.btnDiv)
        val textResultado = findViewById<TextView>(R.id.textResultado)

        btnCalcular.setOnClickListener{
            val n1 = edtN1.text.toString().toInt()
            val n2 = edtN2.text.toString().toInt()
            val res = n1+n2
            textResultado.text = "Resultado suma: ${res.toString()}"
        }
        btnResta.setOnClickListener{
            val n1 = edtN1.text.toString().toInt()
            val n2 = edtN2.text.toString().toInt()
            val res = n1-n2
            textResultado.text = "Resultado resta: ${res.toString()}"
        }
        btnMulti.setOnClickListener{
            val n1 = edtN1.text.toString().toInt()
            val n2 = edtN2.text.toString().toInt()
            val res = n1*n2
            textResultado.text = "Resultado multiplicacion: ${res.toString()}"
        }
        btnDiv.setOnClickListener{
            val n1 = edtN1.text.toString().toInt()
            val n2 = edtN2.text.toString().toInt()
            val res = n1/n2
            textResultado.text = "Resultado division: ${res.toString()}"
        }

    }
}