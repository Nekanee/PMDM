package com.example.appcompartirdatos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val edtMensaje = findViewById<EditText>(R.id.edtMensaje)
        val btnDirecto = findViewById<Button>(R.id.btnDirecto)

        val mensaje : String = edtMensaje.text.toString()

        btnDirecto.setOnClickListener { //para que se active el boton al clikar
            val mensaje : String = edtMensaje.text.toString()
            //Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()

            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("envio",mensaje)// recoge el mensaje de la principal y se lo manda a la activity2
            startActivity(intent)

        }

        val btnElige = findViewById<Button>(R.id.btnElige)
        btnElige.setOnClickListener {
            val mensaje : String = edtMensaje.text.toString()
            Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()

            val intent = Intent()
            intent.action = Intent.ACTION_SEND

            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,mensaje)
            startActivity(Intent.createChooser(intent,"Share to"))



        }


    }
}