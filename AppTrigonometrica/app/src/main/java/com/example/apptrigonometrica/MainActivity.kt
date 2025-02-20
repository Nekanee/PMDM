package com.example.apptrigonometrica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apptrigonometrica.ui.theme.AppTrigonometricaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTrigonometricaTheme {
               VistaPrevia()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiApp() {
   var n1 by remember { mutableStateOf("") }
    var n2 by remember { mutableStateOf("") }
    var hipo by remember { mutableStateOf("") }
    var anguloA by remember { mutableStateOf("") }
    var anguloB by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }
    var perimetro by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Calculadora trigonometria") })
        },
        bottomBar = {
            BottomAppBar { Text("Examen móviles") }
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                TextField(
                    value = n1,
                    onValueChange = { n1 = it },
                    label = { Text("Introduce lado a") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = n2,
                    onValueChange = { n2 = it },
                    label = { Text("Introduce lado b") },
                    modifier = Modifier.fillMaxWidth()
                )
                Row {
                    Button(onClick = {
                        val num1 = n1.toDoubleOrNull() ?: 0.0
                        val num2 = n2.toDoubleOrNull() ?: 0.0

                        val ados= Math.pow(num1, 2.0)
                        val bdos= Math.pow(num2, 2.0)
                        val most = Math.sqrt(ados+bdos)

                        hipo = "Hipotenusa : ${most}"
                        area="Área: ${(num1*num2)/2}"
                        perimetro="Perimetro: ${num1+num2+most}"
                        anguloA="Angulo A: ${Math.atan(num2/num1)}"
                        anguloB="Angulo B: ${Math.atan(num1/num2)}"  }
                        ) {

                        Text("Calcular")
                    }


                    Button(onClick = {
                        hipo = ""
                        area=""
                        perimetro=""
                        anguloA=""
                        anguloB=""
                        n1=""
                        n2=""
                    }) {

                        Text("Vacío")
                    }
                }

                Text(
                    text = hipo,
                    modifier = Modifier.padding(top = 16.dp, start = 10.dp)
                )
                Text(
                    text = area,
                    modifier = Modifier.padding(top = 16.dp, start = 10.dp)
                )
                Text(
                    text = perimetro,
                    modifier = Modifier.padding(top = 16.dp, start = 10.dp)
                )
                Text(
                    text = anguloA,
                    modifier = Modifier.padding(top = 16.dp, start = 10.dp)
                )
                Text(
                    text = anguloB,
                    modifier = Modifier.padding(top = 16.dp, start = 10.dp)
                )
            }
        }
    )


}


@Preview(showBackground = true)
@Composable
fun VistaPrevia() {
    AppTrigonometricaTheme {
        MiApp()
    }
}