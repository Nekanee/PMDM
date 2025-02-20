package com.example.appbasedatos

import android.content.ContentValues
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.appbasedatos.ui.theme.AppBaseDatosTheme

class MainActivity : ComponentActivity() {
    private lateinit var ayudanteBaseDatos : AyudanteBaseDatos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ayudanteBaseDatos = AyudanteBaseDatos(this)
        setContent {
            AppBaseDatosTheme {
               MyApp(ayudante=ayudanteBaseDatos)
            }
        }
    }
}

fun insertarUsuaio(ayudante: AyudanteBaseDatos, nombre: String, correo : String): Long {

    val db = ayudante.writableDatabase
    val valores = ContentValues().apply {
        put(AyudanteBaseDatos.COLUMNA_NOMBRE, nombre)
        put(AyudanteBaseDatos.COLUMNA_EMAIL, correo)
    }
    return db.insert(AyudanteBaseDatos.TABLA_USUARIOS, null, valores)

}
fun eliminarUsuario (ayudante:AyudanteBaseDatos, id: Int):Int{
    val db = ayudante.writableDatabase
    return db.delete(
        AyudanteBaseDatos.TABLA_USUARIOS,
        "${AyudanteBaseDatos.COLUMNA_ID}=?",
        arrayOf(id.toString())
    )
}
fun actualizarUsuario(ayudante:AyudanteBaseDatos, id:Int, nombre:String,correo: String):Int{

    val db = ayudante.writableDatabase
    val valores = ContentValues().apply {
        put(AyudanteBaseDatos.COLUMNA_NOMBRE,nombre)
        put(AyudanteBaseDatos.COLUMNA_EMAIL, correo)
    }
    return db.update(
        AyudanteBaseDatos.TABLA_USUARIOS,
        valores,
        "${AyudanteBaseDatos.COLUMNA_ID}=?",
        arrayOf(id.toString())
    )

}

fun obtenerTodosLosResultados(ayudante: AyudanteBaseDatos):List<Triple<Int,String,String>>{
    val db = ayudante.readableDatabase
    val cursor = db.query(
        AyudanteBaseDatos.TABLA_USUARIOS,
        arrayOf(AyudanteBaseDatos.COLUMNA_ID,AyudanteBaseDatos.COLUMNA_NOMBRE,AyudanteBaseDatos.COLUMNA_EMAIL),
        null,
        null,
        null,
        null,
        null
    )
    val usuarios = mutableListOf<Triple<Int,String,String>>()
    while(cursor.moveToNext()){
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_ID))
        val nombre = cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_NOMBRE))
        val correo = cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_EMAIL))
        usuarios.add(Triple(id,nombre,correo))
    }
    cursor.close()
    return usuarios
}

@Composable
fun MyApp(ayudante: AyudanteBaseDatos) {

}

@Preview(showBackground = true)
@Composable
fun VistaPrevia() {
    AppBaseDatosTheme {

    }
}