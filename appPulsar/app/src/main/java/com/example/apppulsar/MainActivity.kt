package com.example.apppulsar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apppulsar.ui.theme.AppPulsarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppPulsarTheme {
                MiApp()
            }
        }
    }
}

@Composable
fun MiApp() {
    var acumuladorDinero = remember {
        mutableStateOf(0)
    }
    Surface (modifier= Modifier.fillMaxWidth().fillMaxHeight(),color= Color(0xFF346E7A)){
        Column(modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = "${acumuladorDinero.value}€",style= TextStyle(
                color=Color.White,
                fontSize=35.sp,
                fontWeight = FontWeight.ExtraBold
            ))
            Spacer(modifier = Modifier.height(130.dp))
            CrearCirculo(acumuladorDinero=acumuladorDinero.value){
                nuevoValor -> acumuladorDinero.value = nuevoValor
            }
            if(acumuladorDinero.value > 25){
                Text("Mazo de pastaaaaa")
            }
        }


    }

}
@Composable
fun CrearCirculo(acumuladorDinero: Int, actualizarContador: (Int)-> Unit){
    /*var acumuladorDinero = remember{
        mutableStateOf(0)
    }*/
    Card(modifier=Modifier
        .padding(10.dp)
        .size(130.dp)
        .clickable {
            //acumuladorDinero.value+=1
           actualizarContador(acumuladorDinero+1)
        },
        shape= CircleShape,
        elevation = CardDefaults.cardElevation(4.dp)){

        Column (modifier=Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){

            Text(text="Pulsar $acumuladorDinero", modifier = Modifier)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppPulsarTheme {
        MiApp()
    }
}
@Preview(showBackground = true)
@Composable
fun VistaPreliminar() {
    AppPulsarTheme {
        MiApp()
    }
}
