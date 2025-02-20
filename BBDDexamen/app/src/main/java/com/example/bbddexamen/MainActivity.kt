package com.example.bbddexamen

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
import com.example.bbddexamen.ui.theme.BBDDexamenTheme

class MainActivity : ComponentActivity() {
    private lateinit var ayudanteBaseDatos: AyudanteBaseDatos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ayudanteBaseDatos = AyudanteBaseDatos(this)
        setContent {
            BBDDexamenTheme {
                MiAplicacion(ayudante = ayudanteBaseDatos)
            }
        }
    }
}

@Composable
fun MiAplicacion(ayudante: AyudanteBaseDatos) {


    insertarUsuario(ayudante,1,"Cris","Tiano","1234","1000000")
    insertarUsuario(ayudante,2,"Alba","Sura","02468","3000")
    insertarUsuario(ayudante,3,"Andrés","Trozano","13579","150")
    insertarUsuario(ayudante,4,"Inés","Tornudo","159753","1500")
    insertarUsuario(ayudante,5,"Zacarias","Flores del campo","987654","51000")


    val usuarios = remember { mutableStateListOf<Usuario>() }

    LaunchedEffect(Unit) {
        usuarios.addAll(obtenerTodosLosUsuarios(ayudante))
    }

    var enModoEdicion by remember { mutableStateOf(false) }
    var usuarioSeleccionado by remember { mutableStateOf<Usuario?>(null) }


    Column(modifier = Modifier.fillMaxSize()) {
        PantallaAgregarUsuario(
            nombre = usuarioSeleccionado?.nombre ?: "",
            apellido = usuarioSeleccionado?.apellido ?: "",
            cuenta = usuarioSeleccionado?.cuenta ?: "",
            dinero = usuarioSeleccionado?.dinero ?: "",
            enModoEdicion = enModoEdicion,
            onNombreCambio = { nuevoNombre ->
                usuarioSeleccionado = usuarioSeleccionado?.copy(nombre = nuevoNombre) ?: Usuario(0, nuevoNombre, "","","")
            },
            onApellidoCambio = { nuevoApellido ->
                usuarioSeleccionado = usuarioSeleccionado?.copy(apellido = nuevoApellido) ?: Usuario(0, "", nuevoApellido,"","") },
            onCuentaCambio = { nuevaCuenta ->
                usuarioSeleccionado = usuarioSeleccionado?.copy(cuenta = nuevaCuenta) ?: Usuario(0, "", "",nuevaCuenta,"") },
            onDineroCambio = { nuevoDinero ->
                usuarioSeleccionado = usuarioSeleccionado?.copy(dinero = nuevoDinero) ?: Usuario(0, "", "","",nuevoDinero) },
            onGuardar = {
                if (enModoEdicion) {
                    usuarioSeleccionado?.let { usuario ->
                        actualizarUsuario(ayudante, usuario.id, usuario.nombre, usuario.apellido, usuario.cuenta, usuario.dinero)
                        val index = usuarios.indexOfFirst { it.id == usuario.id }
                        if (index != -1) {
                            usuarios[index] = usuario
                        }
                    }
                } else {
                    val id = insertarUsuario(ayudante,usuarioSeleccionado?.id ?: 0, usuarioSeleccionado?.nombre ?:"", usuarioSeleccionado?.apellido ?: "", usuarioSeleccionado?.cuenta ?: "", usuarioSeleccionado?.dinero ?: "").toInt()
                    usuarios.add(Usuario(id, usuarioSeleccionado?.nombre ?: "", usuarioSeleccionado?.apellido ?: "", usuarioSeleccionado?.cuenta ?: "", usuarioSeleccionado?.dinero ?: ""))
                }
                enModoEdicion = false
                usuarioSeleccionado = null
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        PantallaListaUsuarios(
            ayudante = ayudante,
            usuarios = usuarios,
            onActualizar = { id, nombre, apellido, cuenta,dinero ->
                enModoEdicion = true
                usuarioSeleccionado = Usuario(id, nombre ,apellido, cuenta,dinero)
            }
        )
    }
}

@Composable
fun PantallaAgregarUsuario(

    nombre: String,
    apellido: String,
    cuenta: String,
    dinero: String,
    enModoEdicion: Boolean,
    onNombreCambio: (String) -> Unit,
    onApellidoCambio: (String) -> Unit,
    onCuentaCambio: (String) -> Unit,
    onDineroCambio: (String) -> Unit,
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
            value = apellido,
            onValueChange = onApellidoCambio,
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = cuenta,
            onValueChange = onCuentaCambio,
            label = { Text("Dinero") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = dinero,
            onValueChange = onDineroCambio,
            label = { Text("Cuenta") },
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
    onActualizar: (Int, String, String, String,String) -> Unit
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
                    Text(text = "Apellido: ${usuario.apellido}", style = MaterialTheme.typography.bodySmall)
                    Text(text = "Cuenta: ${usuario.cuenta}", style = MaterialTheme.typography.bodySmall)
                    Text(text = "Dinero: ${usuario.dinero}", style = MaterialTheme.typography.bodySmall)
                }
                Row {
                    Button(onClick = { onActualizar(usuario.id, usuario.nombre, usuario.apellido, usuario.cuenta, usuario.dinero) }) {
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
        arrayOf(AyudanteBaseDatos.COLUMNA_ID, AyudanteBaseDatos.COLUMNA_NOMBRE, AyudanteBaseDatos.COLUMNA_APELLIDO, AyudanteBaseDatos.COLUMNA_CUENTA, AyudanteBaseDatos.COLUMNA_DINERO),
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
        val apellido = cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_APELLIDO))
        val cuenta = cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_CUENTA))
        val dinero = cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_DINERO))
        usuarios.add(Usuario(id, nombre, apellido, cuenta,dinero))
    }
    cursor.close()
    return usuarios
}

fun insertarUsuario(ayudante: AyudanteBaseDatos, id:Int, nombre: String, apellido: String, cuenta: String, dinero: String): Long {
    val db = ayudante.writableDatabase
    val valores = ContentValues().apply {
        put(AyudanteBaseDatos.COLUMNA_ID, id)
        put(AyudanteBaseDatos.COLUMNA_NOMBRE, nombre)
        put(AyudanteBaseDatos.COLUMNA_APELLIDO, apellido)
        put(AyudanteBaseDatos.COLUMNA_CUENTA, cuenta)
        put(AyudanteBaseDatos.COLUMNA_DINERO, dinero)
    }
    return db.insert(AyudanteBaseDatos.TABLA_USUARIOS, null, valores)
}
fun actualizarUsuario(ayudante: AyudanteBaseDatos, id: Int, nombre: String, apellido: String, cuenta: String, dinero: String): Int {
    val db = ayudante.writableDatabase
    val valores = ContentValues().apply {
        put(AyudanteBaseDatos.COLUMNA_NOMBRE, nombre)
        put(AyudanteBaseDatos.COLUMNA_APELLIDO, apellido)
        put(AyudanteBaseDatos.COLUMNA_CUENTA, cuenta)
        put(AyudanteBaseDatos.COLUMNA_DINERO, dinero)
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


@Preview(showBackground = true)
@Composable
fun VistaPrevia() {
    BBDDexamenTheme {

    }
}