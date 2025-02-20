package com.example.apprestrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apprestrofit.ui.theme.AppRestroFitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppRestroFitTheme {
                val vistaModelo: AlumnoViewModel = viewModel()
                MyApp(vistaModelo)
            }
        }
    }
}
@Composable
fun MyApp(vistaModelo: AlumnoViewModel) {
    val listaAlumnos by vistaModelo.alumnos.collectAsState()
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(listaAlumnos) { alumno ->
            ItemAlumno(alumno)
        }
    }
}

@Composable
fun ItemAlumno(alumno: Alumno) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = alumno.nombre, style = MaterialTheme.typography.bodyLarge)
            Text(text = alumno.mail, style = MaterialTheme.typography.bodyMedium)
        }
    }
}


