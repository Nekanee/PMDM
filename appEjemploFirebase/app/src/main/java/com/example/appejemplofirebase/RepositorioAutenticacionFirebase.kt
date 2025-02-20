package com.example.appejemplofirebase
import com.google.firebase.auth.FirebaseAuth

class RepositorioAutenticacionFirebase {
    private val autenticacionFirebase: FirebaseAuth = FirebaseAuth.getInstance()

    // Función para iniciar sesión con correo y contraseña
    fun iniciarSesion(correo: String, contrasena: String, enResultado: (Boolean, String?) -> Unit) {
        autenticacionFirebase.signInWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener { tarea ->
                if (tarea.isSuccessful) enResultado(true, null)
                else enResultado(false, tarea.exception?.localizedMessage)
            }
    }

    // Función para registrar un nuevo usuario
    fun registrar(correo: String, contrasena: String, enResultado: (Boolean, String?) -> Unit) {
        autenticacionFirebase.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener { tarea ->
                if (tarea.isSuccessful) enResultado(true, null)
                else enResultado(false, tarea.exception?.localizedMessage)
            }
    }

    // Función para cerrar sesión
    fun cerrarSesion() {
        autenticacionFirebase.signOut()
    }

    // Obtener el usuario autenticado
    fun obtenerUsuarioActual() = autenticacionFirebase.currentUser
}