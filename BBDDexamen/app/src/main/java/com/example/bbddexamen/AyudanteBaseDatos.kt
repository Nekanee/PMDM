package com.example.bbddexamen

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AyudanteBaseDatos(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME="empresa5.db"
        private const val DATABASE_VERSION= 1
        const val TABLA_USUARIOS ="banco"
        const val COLUMNA_ID="id"
        const val COLUMNA_NOMBRE ="nombre"
        const val COLUMNA_APELLIDO="apellido"
        const val COLUMNA_CUENTA="cuenta"
        const val COLUMNA_DINERO="dinero"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val crearTabla = """
            CREATE TABLE $TABLA_USUARIOS(
                $COLUMNA_ID INTEGER PRIMARY KEY,
                $COLUMNA_NOMBRE TEXT NOT NULL,
                $COLUMNA_APELLIDO TEXT NOT NULL,
                $COLUMNA_CUENTA  TEXT NOT NULL,
                $COLUMNA_DINERO TEXT NOT NULL)
        """.trimIndent()

        db.execSQL(crearTabla)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_USUARIOS")
        onCreate(db)
    }
}
