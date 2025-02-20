package com.example.appbienvenida

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appbienvenida.ui.theme.AppBienvenidaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppBienvenidaTheme {
                VistaPrevia()
            }
        }
    }
}

@Composable
fun MyApp() {
    Surface(modifier = Modifier.fillMaxSize().fillMaxWidth()){
        Column {
            Row(){
                imagen()
                textos("Bienvenido a Android","¿Empezamos?")

            }
            Column(modifier = Modifier.padding(15.dp)) {
                boton()
            }
        }

    }

}

@Composable
fun textos(titulo:String,subtitulo:String){
    Column {
        Text(titulo,color=MaterialTheme.colorScheme.primary,modifier = Modifier.padding(5.dp))
        Text(subtitulo, color=MaterialTheme.colorScheme.secondary,modifier = Modifier.padding(5.dp), fontFamily = FontFamily.Serif) }
    }
@Composable
fun imagen(){
    Image(
        painterResource(R.drawable.ic_launcher_foreground),
        "Icono de android"
        ,modifier=Modifier.background(Color.LightGray)
            .size(130.dp)
    )
}
@Composable
fun boton() {
    val mostrarContenido = remember { mutableStateOf(false) }

    Column {
        Button(
            onClick = { mostrarContenido.value = true },
            modifier = Modifier
                .padding(start = 110.dp)
                .size(140.dp, 50.dp)
        ) {
            Text("Saber más")
        }

        if (mostrarContenido.value) {
            mostrar()
        }
    }
}

@Composable
fun mostrar(){
    val estadoScroll = rememberScrollState()
    Column (modifier = Modifier.verticalScroll(estadoScroll).padding(10.dp).border(3.dp, color = Color.Black)) {

        Text("Proyecto 1",modifier=Modifier.padding(10.dp), fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Android Studio", modifier=Modifier.padding(start=10.dp),fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 2", modifier=Modifier.padding(10.dp),fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Java",modifier=Modifier.padding(start=10.dp), fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 3", modifier=Modifier.padding(10.dp),fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Android Studio",modifier=Modifier.padding(start=10.dp), fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 4",modifier=Modifier.padding(10.dp), fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Java", modifier=Modifier.padding(start=10.dp),fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 5", modifier=Modifier.padding(10.dp),fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Android Studio", modifier=Modifier.padding(start=10.dp),fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Text("Proyecto 1",modifier=Modifier.padding(10.dp), fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Android Studio", modifier=Modifier.padding(start=10.dp),fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 2", modifier=Modifier.padding(10.dp),fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Java",modifier=Modifier.padding(start=10.dp), fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 3", modifier=Modifier.padding(10.dp),fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Android Studio",modifier=Modifier.padding(start=10.dp), fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 4",modifier=Modifier.padding(10.dp), fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Java", modifier=Modifier.padding(start=10.dp),fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 5", modifier=Modifier.padding(10.dp),fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Android Studio", modifier=Modifier.padding(start=10.dp),fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Text("Proyecto 1",modifier=Modifier.padding(10.dp), fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Android Studio", modifier=Modifier.padding(start=10.dp),fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 2", modifier=Modifier.padding(10.dp),fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Java",modifier=Modifier.padding(start=10.dp), fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 3", modifier=Modifier.padding(10.dp),fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Android Studio",modifier=Modifier.padding(start=10.dp), fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 4",modifier=Modifier.padding(10.dp), fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Java", modifier=Modifier.padding(start=10.dp),fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 5", modifier=Modifier.padding(10.dp),fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Android Studio", modifier=Modifier.padding(start=10.dp),fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Text("Proyecto 1",modifier=Modifier.padding(10.dp), fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Android Studio", modifier=Modifier.padding(start=10.dp),fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 2", modifier=Modifier.padding(10.dp),fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Java",modifier=Modifier.padding(start=10.dp), fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 3", modifier=Modifier.padding(10.dp),fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Android Studio",modifier=Modifier.padding(start=10.dp), fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 4",modifier=Modifier.padding(10.dp), fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Java", modifier=Modifier.padding(start=10.dp),fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Proyecto 5", modifier=Modifier.padding(10.dp),fontSize = 30.sp, fontFamily = FontFamily.Cursive )
        Text("Android Studio", modifier=Modifier.padding(start=10.dp),fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary)

    }
}

@Preview(showBackground = true)
@Composable
fun VistaPrevia() {
    AppBienvenidaTheme {
        MyApp()
    }
}