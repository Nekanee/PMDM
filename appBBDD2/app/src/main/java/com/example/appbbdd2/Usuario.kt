package com.example.appbbdd2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val nombre : String,
    val correo : String,
    val edad : Int,
    val telefono : String,



)