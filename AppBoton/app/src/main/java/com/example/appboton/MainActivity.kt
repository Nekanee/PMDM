package com.example.appboton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appboton.ui.theme.AppBotonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppBotonTheme {
                VistaPrevia()
            }
        }
    }
}

@Composable
fun MyApp() {
    Surface(modifier=Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()) {
        Column {
            Row {
                imagen()
                textos("Buenos días","Esta es la App boton")

            }
            Boton()
        }


    }

}
@Composable
fun textos (titulo:String, subtitulo:String){
    Column {
        Text(titulo, fontSize = 30.sp, fontFamily = FontFamily.SansSerif,modifier=Modifier.padding(start = 10.dp,top=10.dp))
        Text(subtitulo, fontSize = 15.sp,modifier=Modifier.padding(start=10.dp))
    }
}
@Composable
fun imagen(){
    Image(
        painterResource(R.drawable.ic_launcher_foreground),
        "Imagen de android",
        modifier=Modifier.size(100.dp).background(Color.DarkGray)

    )
}
@Composable
fun Boton(){
    val estadoBoton = remember { mutableStateOf(false) }
    Button(onClick = {estadoBoton.value=true},modifier=Modifier.size(130.dp,60.dp).padding(10.dp)) {
        Text("Pulsa")
    }
    if(estadoBoton.value){
        Mostrar()
    }
}
@Composable
fun Mostrar(){
    Text("Esto es lo que hay que mstrar")
}
@Preview(showBackground = true)
@Composable
fun VistaPrevia() {
    AppBotonTheme {
        MyApp()
    }
}