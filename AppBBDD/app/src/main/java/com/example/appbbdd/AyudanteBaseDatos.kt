package com.example.appbbdd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AyudanteBaseDatos(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME="empresa.db"
        private const val DATABASE_VERSION= 1
         const val TABLA_USUARIOS ="usuarios"
         const val COLUMNA_ID="id"
         const val COLUMNA_NOMBRE ="nombre"
         const val COLUMNA_CORREO="correo"
    }

    fun getEmail(): String{
        return COLUMNA_CORREO
    }

    override fun onCreate(db: SQLiteDatabase) {
        val crearTabla = """
            CREATE TABLE $TABLA_USUARIOS(
                $COLUMNA_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMNA_NOMBRE TEXT NOT NULL,
                $COLUMNA_CORREO TEXT NOT NULL)
        """.trimIndent()

        db.execSQL(crearTabla)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_USUARIOS")
        onCreate(db)
    }

}