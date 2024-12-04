package com.example.appsumacompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.example.appsumacompose.ui.theme.AppSumaComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppSumaComposeTheme{
                MiApp()

            }

        }
    }
}

@Composable
fun MiApp() {
    Surface (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        color = MaterialTheme.colorScheme.primary){

        var entrada1 by remember { mutableStateOf("") }
        var entrada2 by remember { mutableStateOf("") }
        var resultado by remember { mutableStateOf(0.0) }

        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Row(modifier = Modifier.padding(16.dp)){
                Text("Introduce el número 1")

                TextField(
                    value = entrada1,
                    onValueChange = {entrada1 = it},
                    label = { Text("N1")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Row(modifier = Modifier.padding(16.dp)){
                Text("Introduce el número 2")
                TextField(
                    value = entrada2,
                    onValueChange = {entrada2 = it},
                    label = { Text("N1")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Row(modifier = Modifier.padding(16.dp)){

                Button(onClick={resultado =entrada1.toDouble() +entrada2.toDouble()},
                        colors= ButtonDefaults.buttonColors(Color.DarkGray),
                    modifier = Modifier.padding(5.dp)
                ){
                    Text("Sumar")
                }
                Button(onClick={resultado =entrada1.toDouble() -entrada2.toDouble()},
                    colors= ButtonDefaults.buttonColors(Color.DarkGray),
                    modifier = Modifier.padding(5.dp)){
                    Text("Restar")
                }
            }
            Text(text="Resultado ${resultado.toString()}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VistaPreliminar() {
    AppSumaComposeTheme{
        MiApp()
    }
}