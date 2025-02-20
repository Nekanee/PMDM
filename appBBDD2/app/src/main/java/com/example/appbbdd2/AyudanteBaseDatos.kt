package com.example.appbbdd2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AyudanteBaseDatos(context: Context) : SQLiteOpenHelper(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS) {

    companion object {
        const val NOMBRE_BASE_DATOS = "Usuarios.db"
        const val VERSION_BASE_DATOS = 2 // Incrementado para los nuevos campos
        const val TABLA_USUARIOS = "usuarios"

        const val COLUMNA_ID = "id"
        const val COLUMNA_NOMBRE = "nombre"
        const val COLUMNA_CORREO = "correo"
        const val COLUMNA_EDAD = "edad"
        const val COLUMNA_TELEFONO = "telefono"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tabla usuarios con los 5 campos
        val crearTablaUsuarios = """
            CREATE TABLE $TABLA_USUARIOS (
                $COLUMNA_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMNA_NOMBRE TEXT NOT NULL,
                $COLUMNA_CORREO TEXT NOT NULL,
                $COLUMNA_EDAD INTEGER DEFAULT 0,
                $COLUMNA_TELEFONO TEXT DEFAULT ''
            )
        """
        db.execSQL(crearTablaUsuarios)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            // Añadimos los nuevos campos en la versión 2
            db.execSQL("ALTER TABLE $TABLA_USUARIOS ADD COLUMN $COLUMNA_EDAD INTEGER DEFAULT 0")
            db.execSQL("ALTER TABLE $TABLA_USUARIOS ADD COLUMN $COLUMNA_TELEFONO TEXT DEFAULT ''")
        }
    }
}