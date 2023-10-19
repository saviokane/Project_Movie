package com.maxpayneman.project_movie.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.maxpayneman.aulayt_8.databinding.ActivityMainBinding
import com.maxpayneman.project_movie.ViewModel.UsuarioController
import com.maxpayneman.project_movie.View.FilmesSearchMainActivity

class LoginMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonLogar.setOnClickListener {
            val user = binding.user.text.toString()
            val senha = binding.senha.text.toString()

            if (user.isNotEmpty() && senha.isNotEmpty()) {
                signIn(user, senha)
            } else {
                Toast.makeText(this, "Preencha o usuário e senha", Toast.LENGTH_SHORT).show()
            }
        }

        binding.criarConta.setOnClickListener() {
            startActivity(Intent(this, CadastroMainActivity::class.java))
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login bem-sucedido, redirecione para a próxima atividade
                    val user = auth.currentUser
                    Toast.makeText(this, "Login bem-sucedido !!!", Toast.LENGTH_SHORT).show()
                    updateUI(user)
                    startActivity(Intent(this, FilmesSearchMainActivity::class.java))
                } else {
                    // Se o login falhar, exiba uma mensagem de erro
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(this, "Falha na autenticação.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        // Atualize a interface do usuário conforme necessário
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}
