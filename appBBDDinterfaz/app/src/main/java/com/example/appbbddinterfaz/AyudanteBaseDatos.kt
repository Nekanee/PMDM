package com.example.appbbddinterfaz

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class AyudanteBaseDatos (context: Context) : SQLiteOpenHelper(context, NOMBRE_BASE_DATOS,null,
    VERSION_BASE_DATOS) {

    companion object{
        const val NOMBRE_BASE_DATOS="empresa.db"
        const val VERSION_BASE_DATOS=1
        const val TABLA_USUARIOS="usuarios"
        const val COLUMNA_ID="id"
        const val COLUMNA_NOMBRE="nombre"
        const val COLUMNA_EMAIL="correo"
    }
    override fun onCreate(db: SQLiteDatabase) {
        val crearTabla = """
            CREATE TABLE $TABLA_USUARIOS(
            $COLUMNA_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMNA_NOMBRE TEXT NOT NULL,
            $COLUMNA_EMAIL TEXT NOT NULL )
            """
        db.execSQL(crearTabla)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_USUARIOS")
        onCreate(db)
    }
}