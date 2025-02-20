package com.example.firebase

import com.google.firebase.auth.FirebaseAuth

class RepositorioAutenticationFirebase {

    private val autenticationFirebase : FirebaseAuth = FirebaseAuth.getInstance();

    fun iniciarSesion (correo: String , contrasena: String , enResultado: (Boolean, String?) -> Unit){
        autenticationFirebase.signInWithEmailAndPassword(correo,contrasena)
            .addOnCompleteListener{ tarea ->
                if(tarea.isSuccessful) enResultado (true,null)
                else enResultado (false, tarea.exception?.localizedMessage)
            }
    }

    fun registrar(correo: String,contrasena: String,enResultado: (Boolean, String?) -> Unit){
        autenticationFirebase.createUserWithEmailAndPassword(correo,contrasena)
            .addOnCompleteListener{ tarea->
                if(tarea.isSuccessful) enResultado(true,null)
                else enResultado (false, tarea.exception?.localizedMessage)
            }
    }

    fun cerrarSession(){
        autenticationFirebase.signOut()
    }

    fun obtenerUsuarioActual() = autenticationFirebase.currentUser
}