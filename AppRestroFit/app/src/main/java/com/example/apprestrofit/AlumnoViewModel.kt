package com.example.apprestrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlumnoViewModel : ViewModel() {
    private val _alumnos = MutableStateFlow<List<Alumno>>(emptyList())
    val alumnos = _alumnos.asStateFlow()

    init {
        cargarAlumnos()
    }
    private fun cargarAlumnos(){
        viewModelScope.launch {
            try {
                _alumnos.value = RetroFitCliente.instancia.obtenerAlumnos()
            } catch (e: Exception) {
                println("No tira"+e.message)

            }
        }
    }
}