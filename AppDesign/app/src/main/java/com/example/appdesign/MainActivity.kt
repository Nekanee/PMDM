package com.example.appdesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdesign.ui.theme.AppDesignTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDesignTheme {
                MyApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    Scaffold (
        topBar = { TopAppBar(title= {Text("Barra superior")},
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )

            ) },
        bottomBar = { BottomAppBar(){ Text("Barra inferior") } },
        floatingActionButton = {
            FloatingActionButton(onClick = {})
            {
                Icon(Icons.Filled.AddCircle , contentDescription = "Ckeck")
            }
        },
        content = {padding ->
            Box(modifier = Modifier.padding(padding)){
                Surface(modifier= Modifier
                    .fillMaxSize(),
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.primary,
                    shadowElevation = 10.dp,
                    border = BorderStroke(5.dp,Color.Black)
                ) {
                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        Text(
                            "¿Cómo están ustedes?",
                            textAlign = TextAlign.Center,
                            fontSize = 45.sp,
                            lineHeight = 50.sp)


                        Icon(Icons.Filled.Face, contentDescription = "cara",
                            modifier=Modifier.width(50.dp).height(50.dp))
                        TextField("Intoduce un valor", onValueChange = {/*Acccion*/})
                    }
                }
            }
        }

    )



     /*Row(modifier = Modifier.padding(10.dp),
       verticalAlignment = Alignment.CenterVertically){
         Text("Fila")
         Text("Hola a todos")
         Text("Como están ustedes ?")
     }

     Box(){
         Text("*   De la tele")
         Icon(Icons.Filled.Check , contentDescription = "Ckeck")
     }*/
 }





@Preview(showBackground = true)
@Composable
fun VistaPrevia() {
 AppDesignTheme {
     MyApp()
 }
}