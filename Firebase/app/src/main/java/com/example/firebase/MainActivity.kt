package com.example.firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebase.ui.theme.FirebaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebaseTheme {
                MaterialTheme{
                    NavegacionAplication(RepositorioAutenticationFirebase())
                }
            }
        }
    }
}

@Composable
fun NavegacionAplication (repositorio: RepositorioAutenticationFirebase) {

    val navController = rememberNavController()

    NavHost(navController, startDestination = "login"){
      composable("login"){PantallaLogin(repositorio,navController)}
      composable("inicio"){PantallaInicio(repositorio,navController)}
    }

}

@Composable
fun PantallaLogin(repositorio: RepositorioAutenticationFirebase, navController: NavController){
    var correo by remember{ mutableStateOf("")}
    var contrasena by remember{ mutableStateOf("")}
    var mensajeError by remember {mutableStateOf<String?> (null)}
    var cargando by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Inicia Sesion ", style =MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value=correo,
            onValueChange = {correo = it},
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value=contrasena,
            onValueChange = {contrasena = it},
            label = { Text("Contrasena") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                cargando = true
                repositorio.iniciarSesion(correo,contrasena){exito,error ->
                    cargando = false
                    if(exito){
                        navController.navigate("inicio"){
                            popUpTo("login"){inclusive=true}
                        }
                    }else{
                        mensajeError = error ?: "Error al inicar sesion"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Iniciar Sesion")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                cargando = true
                repositorio.registrar(correo,contrasena){exito,error ->
                    cargando = false
                    if(exito){
                        navController.navigate("inicio"){
                            popUpTo("login"){inclusive=true}
                        }
                    }else{
                        mensajeError = error ?: "Error en el registro"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Registrarse")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if(cargando){
            CircularProgressIndicator()
        }
        mensajeError?.let{ msg ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = msg, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun PantallaInicio(repositorio: RepositorioAutenticationFirebase, navController: NavController){

    val usuario = repositorio.obtenerUsuarioActual()

    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text="Bienvenido , ${usuario?.email ?: "Usuario"}",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            repositorio.cerrarSession()
            navController.navigate("login"){
                popUpTo("inicio") { inclusive=true }
            }
        }){
            Text("Cerrar Sesión")
        }
    }
}