package com.maxpayneman.project_movie.View

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.maxpayneman.aulayt_8.databinding.ActivityCadastroMainBinding
import com.maxpayneman.project_movie.ViewModel.UsuarioController

class CadastroMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        val usuario = UsuarioController.getInstance()

        fun reload() {
            auth.currentUser!!.reload().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Usuário recarregado com sucesso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Falha ao recarregar o usuário", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.buttonRealizarCadastro.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val senha = binding.editSenha.text.toString()
            val email = binding.editUser.text.toString()
            val idade = binding.editIdade.text.toString().toInt()

            if (nome.isEmpty() || senha.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos !!!", Toast.LENGTH_SHORT).show()
            } else {

                auth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Registro bem-sucedido, atualize a interface do usuário

                            //val snackbar = Snackbar.make(,"Cadastro realizado com sucesso !!!",Snackbar.LENGTH_SHORT)
                            //snackbar.setBackgroundTint(Color.GREEN)
                            //snackbar.show()
                            Toast.makeText(
                                baseContext,
                                "Usuario Cadastrado com sucesso !!!",
                                Toast.LENGTH_SHORT
                            ).show()
                            updateUI(null)
                            val user = auth.currentUser
                            updateUI(user)
                        } else {
                            // Se o registro falhar, exiba uma mensagem de erro
                            Toast.makeText(
                                baseContext,
                                "Falha na autenticação.",
                                Toast.LENGTH_SHORT
                            ).show()
                            updateUI(null)
                        }
                    }
            }
        }

        binding.buttonVoltar.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        // Verifique se o usuário está logado e atualize a interface do usuário em conformidade
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun reload() {
        val user = auth.currentUser

        user?.reload()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Usuário delogado com sucesso", Toast.LENGTH_SHORT).show()
                    updateUI(user)
                } else {
                    Toast.makeText(this, "Falha ao recarregar o usuário", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }


    private fun updateUI(user: FirebaseUser?) {
        // Atualize a interface do usuário conforme necessário
    }
}
