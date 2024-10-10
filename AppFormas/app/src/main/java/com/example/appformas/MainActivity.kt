package com.example.appformas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edtLado = findViewById<EditText>(R.id.etLado)
        val edtBase = findViewById<EditText>(R.id.etTrianguloBase)
        val edtAltura = findViewById<EditText>(R.id.etTrainguloAltura)
        val edtRadio = findViewById<EditText>(R.id.etRadio)

        val bCuadrado = findViewById<Button>(R.id.btCuadrado)
        val bTraingulo = findViewById<Button>(R.id.btTraingulo)
        val bCirculo = findViewById<Button>(R.id.btCirculo)

        bCuadrado.setOnClickListener{

            val lado: Double = edtLado.text.toString().toDouble()

            val intent = Intent(this, Cuadrado::class.java)
            intent.putExtra("ladoCuadrado",lado)
            startActivity(intent)

        }
        bTraingulo.setOnClickListener{

            val base: Double = edtBase.text.toString().toDouble()
            val altura: Double = edtAltura.text.toString().toDouble()

            val intent = Intent(this, Triangulo::class.java)
            intent.putExtra("baseTriangulo",base)
            intent.putExtra("alturaTriangulo",altura)
            startActivity(intent)

        }

        bCirculo.setOnClickListener{

            val radio: Double = edtRadio.text.toString().toDouble()


            val intent = Intent(this, Circulo::class.java)
            intent.putExtra("radioCirculo",radio)

            startActivity(intent)

        }

        }
    }
