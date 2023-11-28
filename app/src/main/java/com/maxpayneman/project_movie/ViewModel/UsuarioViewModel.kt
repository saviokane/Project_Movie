package com.maxpayneman.project_movie.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.maxpayneman.project_movie.Model.Usuario

class UsuarioViewModel : ViewModel() {

        private val auth = FirebaseAuth.getInstance()
        private val db = FirebaseFirestore.getInstance()

        fun cadastrarUsuario(usuario: Usuario, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
            auth.createUserWithEmailAndPassword(usuario.email,usuario.senha).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val uid = user?.uid

                    val users = hashMapOf(
                        "nome" to usuario.nome,
                        "idade" to usuario.idade.toString(),
                        "email" to usuario.email,
                        "senha" to usuario.senha
                    )

                    uid?.let {
                        db.collection("Usuarios").document(it).collection("users").document("Usuario")
                            .set(users)
                            .addOnCompleteListener {
                                onSuccess.invoke()
                            }
                    }
                } else {
                    onFailure.invoke("Erro ao cadastrar o usuário: ${task.exception?.message}")
                }
            }
        }
    }
