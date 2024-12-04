package com.example.appcambiovistas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appcambiovistas.ui.theme.AppCambioVistasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppCambioVistasTheme {
                NavegacionEntreVentanas()
            }
        }
    }
}

@Composable
fun NavegacionEntreVentanas() {
    val controladorNavegacion = rememberNavController()
    NavHost(navController = controladorNavegacion, startDestination = "Inicio"){
        composable("inicio"){ MiApp(controladorNavegacion)}
        composable("detalles"){ DetallesProducto(controladorNavegacion)}
    }
}

@Composable
fun DetallesProducto(navController: NavController) {
    Column() {
        Text("Esto son los detalles cuando pinchas en el botón")
    }
}

@Composable
fun MiApp(navController: NavController) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Button(modifier = Modifier.padding(5.dp),
                onClick = {
                    navController.navigate("detalles")
                }) {
                Text("Ir a detalles del producto")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppCambioVistasTheme {
        NavegacionEntreVentanas()
    }
}