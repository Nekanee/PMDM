package com.example.appbbdd2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuarios")
    suspend fun obtenerTodosLosUsuarios(): List<Usuario>
    @Insert
    suspend fun insertarUsuario(usuario: Usuario) : Long
    @Update
    suspend fun actualizarUsuario (usuario: Usuario)
    @Delete
    suspend fun eliminarUsuario (usuario: Usuario)

}