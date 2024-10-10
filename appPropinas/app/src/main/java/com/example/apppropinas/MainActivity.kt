package com.example.apppropinas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        btnEnviar.setOnClickListener {
            val swRedondeo = findViewById<Switch>(R.id.swRedondeo)
            val rG1 = findViewById<RadioGroup>(R.id.rG1)
            val rb15 = findViewById<RadioButton>(R.id.rb15)
            val rb18 = findViewById<RadioButton>(R.id.rb18)
            val rb20 = findViewById<RadioButton>(R.id.rb20)
            val txtpropina = findViewById<TextView>(R.id.txtPropina)
            val etPrecio = findViewById<EditText>(R.id.etPrecio)
            val txtTotal = findViewById<TextView>(R.id.txtTotal)

            val porcentaje:Double
            if(rG1.checkedRadioButtonId==R.id.rb20){
                porcentaje=1.2

            }else if(rG1.checkedRadioButtonId==R.id.rb18){
                porcentaje =1.18
            }
            else{
                porcentaje=1.15
            }

            var subTotal = etPrecio.text.toString().toDouble()*porcentaje

            if (swRedondeo.isChecked){

                subTotal= ceil(subTotal)
            }
            txtTotal.setText(subTotal.toString())
        }


    }
}