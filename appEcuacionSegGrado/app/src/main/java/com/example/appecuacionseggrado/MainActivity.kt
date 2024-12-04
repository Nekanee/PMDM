package com.example.appecuacionseggrado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appecuacionseggrado.ui.theme.AppEcuacionSegGradoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppEcuacionSegGradoTheme {
                MiApp()
            }
        }
    }
}

@Composable
fun MiApp() {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        color = MaterialTheme.colorScheme.secondary){

        var entrada1 by remember { mutableStateOf("") }
        var entrada2 by remember { mutableStateOf("") }
        var entrada3 by remember { mutableStateOf("") }

        var resultado2 by remember { mutableStateOf(0.0) }

        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Row(modifier = Modifier.padding(60.dp)){
                TextField(
                    modifier = Modifier.width(30.dp)
                        .height(20.dp),
                    value= entrada1,
                    onValueChange = {entrada1 = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Text(" X²+ ")
               TextField(
                   modifier = Modifier.width(30.dp)
                       .height(20.dp),
                    value= entrada2,
                    onValueChange = {entrada2 = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(" X+ ")
                TextField(
                    modifier = Modifier.width(30.dp)
                        .height(20.dp),
                    value= entrada3,
                    onValueChange = {entrada3 = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(" =")

        }

            //aqui la formula
            Button(onClick={var resultado1= x1(entrada1,entrada2,entrada3)

                resultado2 =entrada1.toDouble() +entrada2.toDouble()},
                colors= ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier.padding(5.dp)
            ){
                Text("Calcular")
            }
            Row(modifier = Modifier.padding(20.dp)){
                Text("X1 = ${resultado1.toString()}")
            }
            Row(modifier = Modifier.padding(20.dp)){
                Text("X2 = ${resultado2.toString()}")
            }

        }

    }
}

fun x1(entrada1: String, entrada2: String, entrada3: String): Double {

    var res by remember { mutableStateOf(0.0) }
    val discriminante = Math.pow(entrada2, 2.0) - 4 * entrada1 * entrada3

    if (discriminante >= 0) {
        res = (-entrada2 + Math.sqrt(discriminante)) / (2 * entrada1)
    } else {
        res = Double.NaN
    }
    return res
}

@Composable
fun x2(entrada1: Double, entrada2: Double, entrada3: Double): Double {

    var res by remember { mutableStateOf(0.0) }
    val discriminante = Math.pow(entrada2, 2.0) - 4 * entrada1 * entrada3

    if (discriminante >= 0) {
        res = (-entrada2 - Math.sqrt(discriminante)) / (2 * entrada1)
    } else {
        res = Double.NaN
    }
    return res
}

@Preview(showBackground = true)
@Composable
fun VistaPreliminar() {
    AppEcuacionSegGradoTheme{
        MiApp()
    }
}