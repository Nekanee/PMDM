package com.example.appbotones

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val btApagado = findViewById<ToggleButton>(R.id.tb_off)
        val txtApagado = findViewById<TextView>(R.id.txtResultadoOff)
        val btSwitch = findViewById<Switch>(R.id.sw)
        val txtSwitch = findViewById<TextView>(R.id.txtSwitch)
        val btBluetooth = findViewById<Button>(R.id.bt_bluetooth)
        val imageButton = findViewById<ImageButton>(R.id.ib_bebe)



    }
}