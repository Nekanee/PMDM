package com.example.appbbdd
import android.content.ContentValues
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appbbdd.ui.theme.AppBBDDTheme

class MainActivity : ComponentActivity() {
    private lateinit var ayudanteBaseDatos: AyudanteBaseDatos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializa el ayudante de base de datos
        ayudanteBaseDatos = AyudanteBaseDatos(this)

        setContent {
            AppBBDDTheme {
                MiAplicacion(ayudante = ayudanteBaseDatos)
            }
        }
    }
}
@Composable
fun MiAplicacion(ayudante: AyudanteBaseDatos) {
    val usuarios = remember { mutableStateListOf<Usuario>() }

    LaunchedEffect(Unit) {
        usuarios.addAll(obtenerTodosLosUsuarios(ayudante))
    }

    var enModoEdicion by remember { mutableStateOf(false) }
    var usuarioSeleccionado by remember { mutableStateOf<Usuario?>(null) }

    /*var idSeleccionado by remember { mutableStateOf(0) }
    var nombreSeleccionado by remember { mutableStateOf("") }
    var correoSeleccionado by remember { mutableStateOf("") }*/

    Column(modifier = Modifier.fillMaxSize()) {
        PantallaAgregarUsuario(
            nombre = usuarioSeleccionado?.nombre ?: "",
            correo = usuarioSeleccionado?.correo ?: "",
            enModoEdicion = enModoEdicion,
            onNombreCambio = { nuevoNombre ->
                usuarioSeleccionado = usuarioSeleccionado?.copy(nombre = nuevoNombre) ?: Usuario(0, nuevoNombre, "")
            },
            onCorreoCambio = { nuevoCorreo ->
                usuarioSeleccionado = usuarioSeleccionado?.copy(correo = nuevoCorreo) ?: Usuario(0, "", nuevoCorreo) },
            onGuardar = {
                if (enModoEdicion) {
                    usuarioSeleccionado?.let { usuario ->
                        actualizarUsuario(ayudante, usuario.id, usuario.nombre, usuario.correo)
                        val index = usuarios.indexOfFirst { it.id == usuario.id }
                        if (index != -1) {
                            usuarios[index] = usuario
                        }
                    }
                } else {
                    val id = insertarUsuario(ayudante, usuarioSeleccionado?.nombre ?:"", usuarioSeleccionado?.correo ?: "").toInt()
                    usuarios.add(Usuario(id, usuarioSeleccionado?.nombre ?: "", usuarioSeleccionado?.correo ?: ""))
                }
                enModoEdicion = false
                usuarioSeleccionado = null
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        PantallaListaUsuarios(
            ayudante = ayudante,
            usuarios = usuarios,
            onActualizar = { id, nombre, correo ->
                enModoEdicion = true
                usuarioSeleccionado = Usuario(id, nombre, correo)
            }
        )
    }
}


@Composable
fun PantallaAgregarUsuario(
    nombre: String,
    correo: String,
    enModoEdicion: Boolean,
    onNombreCambio: (String) -> Unit,
    onCorreoCambio: (String) -> Unit,
    onGuardar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = nombre,
            onValueChange = onNombreCambio,
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = correo,
            onValueChange = onCorreoCambio,
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onGuardar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (enModoEdicion) "Actualizar Usuario" else "Guardar Usuario")
        }
    }
}





@Composable
fun PantallaListaUsuarios(
    ayudante: AyudanteBaseDatos,
    usuarios: MutableList<Usuario>,
    onActualizar: (Int, String, String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(usuarios) { usuario ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "ID: ${usuario.id}", style = MaterialTheme.typography.bodySmall)
                    Text(text = "Nombre: ${usuario.nombre}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Correo: ${usuario.correo}", style = MaterialTheme.typography.bodySmall)
                }
                Row {
                    Button(onClick = { onActualizar(usuario.id, usuario.nombre, usuario.correo) }) {
                        Text("Editar")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        eliminarUsuario(ayudante, usuario.id) // Elimina directamente al usuario
                        usuarios.remove(usuario)
                    }) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}


fun obtenerTodosLosUsuarios(ayudante: AyudanteBaseDatos): List<Usuario> {
    val db = ayudante.readableDatabase
    val cursor = db.query(
        AyudanteBaseDatos.TABLA_USUARIOS,
        arrayOf(AyudanteBaseDatos.COLUMNA_ID, AyudanteBaseDatos.COLUMNA_NOMBRE, AyudanteBaseDatos.COLUMNA_CORREO),
        null,
        null,
        null,
        null,
        null
    )

    val usuarios = mutableListOf<Usuario>()
    while (cursor.moveToNext()) {
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_ID))
        val nombre = cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_NOMBRE))
        val correo = cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_CORREO))
        usuarios.add(Usuario(id, nombre, correo))
    }
    cursor.close()
    return usuarios
}

fun insertarUsuario(ayudante: AyudanteBaseDatos, nombre: String, correo: String): Long {
    val db = ayudante.writableDatabase
    val valores = ContentValues().apply {
        put(AyudanteBaseDatos.COLUMNA_NOMBRE, nombre)
        put(AyudanteBaseDatos.COLUMNA_CORREO, correo)
    }
    return db.insert(AyudanteBaseDatos.TABLA_USUARIOS, null, valores)
}

fun actualizarUsuario(ayudante: AyudanteBaseDatos, id: Int, nombre: String, correo: String): Int {
    val db = ayudante.writableDatabase
    val valores = ContentValues().apply {
        put(AyudanteBaseDatos.COLUMNA_NOMBRE, nombre)
        put(AyudanteBaseDatos.COLUMNA_CORREO, correo)
    }
    return db.update(
        AyudanteBaseDatos.TABLA_USUARIOS,
        valores,
        "${AyudanteBaseDatos.COLUMNA_ID} = ?",
        arrayOf(id.toString())
    )
}
fun eliminarUsuario(ayudante: AyudanteBaseDatos, id: Int): Int {
    val db = ayudante.writableDatabase
    return db.delete(
        AyudanteBaseDatos.TABLA_USUARIOS,
        "${AyudanteBaseDatos.COLUMNA_ID} = ?",
        arrayOf(id.toString())
    )
}


@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    AppBBDDTheme {
    }
}