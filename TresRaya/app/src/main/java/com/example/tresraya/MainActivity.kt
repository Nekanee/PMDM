package com.example.tresraya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TresRayaApp()
        }
    }
}

@Composable
fun TresRayaApp() {
    val posT00 = remember { mutableStateOf("") }
    val posT01 = remember { mutableStateOf("") }
    val posT02 = remember { mutableStateOf("") }
    val posT10 = remember { mutableStateOf("") }
    val posT11 = remember { mutableStateOf("") }
    val posT12 = remember { mutableStateOf("") }
    val posT20 = remember { mutableStateOf("") }
    val posT21 = remember { mutableStateOf("") }
    val posT22 = remember { mutableStateOf("") }
    val turno = remember { mutableStateOf(0) }
    val resultado = remember { mutableStateOf("") }

    // Verificar ganador
    fun verificarGanador(): String {
        if (posT00.value == "X" && posT01.value == "X" && posT02.value == "X") return "X"
        if (posT10.value == "X" && posT11.value == "X" && posT12.value == "X") return "X"
        if (posT20.value == "X" && posT21.value == "X" && posT22.value == "X") return "X"
        if (posT00.value == "X" && posT10.value == "X" && posT20.value == "X") return "X"
        if (posT01.value == "X" && posT11.value == "X" && posT21.value == "X") return "X"
        if (posT02.value == "X" && posT12.value == "X" && posT22.value == "X") return "X"
        if (posT00.value == "X" && posT11.value == "X" && posT22.value == "X") return "X"
        if (posT02.value == "X" && posT11.value == "X" && posT20.value == "X") return "X"
        return ""
    }

    // Hacer jugada
    fun hacerJugada(i: Int, j: Int) {
        if (verificarGanador().isEmpty()) {
            val pos = when {
                i == 0 && j == 0 -> posT00
                i == 0 && j == 1 -> posT01
                i == 0 && j == 2 -> posT02
                i == 1 && j == 0 -> posT10
                i == 1 && j == 1 -> posT11
                i == 1 && j == 2 -> posT12
                i == 2 && j == 0 -> posT20
                i == 2 && j == 1 -> posT21
                i == 2 && j == 2 -> posT22
                else -> null
            }

            pos?.value = if (turno.value % 2 == 0) "X" else "0"
            turno.value++

            if (verificarGanador().isNotEmpty()) {
                resultado.value = "Ganador: X"
            }
        }
    }


    fun reiniciarJuego() {
        posT00.value = ""
        posT01.value = ""
        posT02.value = ""
        posT10.value = ""
        posT11.value = ""
        posT12.value = ""
        posT20.value = ""
        posT21.value = ""
        posT22.value = ""
        turno.value = 0
        resultado.value = ""
    }


    Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight(), ) {
        Column(modifier = Modifier.padding(35.dp)) {
            // Fila 1
            Row {
                Button(modifier = Modifier.padding(5.dp).width(100.dp).height(100.dp), colors = ButtonDefaults.buttonColors(
                    Color(0xFF2AE2E8)), onClick = { hacerJugada(0, 0) }) { Text(posT00.value, fontSize = 20.sp) }
                Button(modifier = Modifier.padding(5.dp).width(100.dp).height(100.dp), colors = ButtonDefaults.buttonColors(
                    Color(0xFF2AE2E8)), onClick = { hacerJugada(0, 1) }) { Text(posT01.value, fontSize = 20.sp) }
                Button(modifier = Modifier.padding(5.dp).width(100.dp).height(100.dp), colors = ButtonDefaults.buttonColors(
                    Color(0xFF2AE2E8)), onClick = { hacerJugada(0, 2) }) { Text(posT02.value, fontSize = 20.sp) }
            }
            // Fila 2
            Row {
                Button(modifier = Modifier.padding(5.dp).width(100.dp).height(100.dp), colors = ButtonDefaults.buttonColors(
                    Color(0xFF2AE2E8)), onClick = { hacerJugada(1, 0) }) { Text(posT10.value, fontSize = 20.sp) }
                Button(modifier = Modifier.padding(5.dp).width(100.dp).height(100.dp), colors = ButtonDefaults.buttonColors(
                    Color(0xFF2AE2E8)), onClick = { hacerJugada(1, 1) }) { Text(posT11.value, fontSize = 20.sp) }
                Button(modifier = Modifier.padding(5.dp).width(100.dp).height(100.dp), colors = ButtonDefaults.buttonColors(
                    Color(0xFF2AE2E8)), onClick = { hacerJugada(1, 2) }) { Text(posT12.value, fontSize = 20.sp) }
            }
            // Fila 3
            Row {
                Button(modifier = Modifier.padding(5.dp).width(100.dp).height(100.dp), colors = ButtonDefaults.buttonColors(
                    Color(0xFF2AE2E8)), onClick = { hacerJugada(2, 0) }) { Text(posT20.value, fontSize = 20.sp) }
                Button(modifier = Modifier.padding(5.dp).width(100.dp).height(100.dp), colors = ButtonDefaults.buttonColors(
                    Color(0xFF2AE2E8)), onClick = { hacerJugada(2, 1) }) { Text(posT21.value, fontSize = 20.sp) }
                Button(modifier = Modifier.padding(5.dp).width(100.dp).height(100.dp), colors = ButtonDefaults.buttonColors(
                    Color(0xFF2AE2E8)), onClick = { hacerJugada(2, 2) }) { Text(posT22.value, fontSize = 20.sp) }
            }

            if (resultado.value.isNotEmpty()) {
                Text(fontSize = 20.sp, text = resultado.value, modifier = Modifier.padding(16.dp))
            }


            Button(modifier=Modifier.padding(110.dp),onClick = { reiniciarJuego() }) {
                Text("Reiniciar",fontSize = 18.sp)
            }
        }
    }
}
