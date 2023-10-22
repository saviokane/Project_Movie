package com.maxpayneman.project_movie.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.maxpayneman.aulayt_8.R
import com.maxpayneman.aulayt_8.databinding.ActivityMainBinding
import com.maxpayneman.project_movie.Model.Filme
import com.maxpayneman.project_movie.Model.UsuarioModel
import com.maxpayneman.project_movie.ViewModel.UsuarioController
import com.maxpayneman.project_movie.View.FilmesSearchMainActivity

class LoginMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



           binding.buttonLogar.setOnClickListener {
               var email = binding.email.text.toString()
               var senha = binding.senha.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty()) {
                signIn(email, senha)
            } else {
                Toast.makeText(this, "Preencha o usuário e senha", Toast.LENGTH_SHORT).show()
            }
        }

        binding.criarConta.setOnClickListener() {
            startActivity(Intent(this, CadastroMainActivity::class.java))
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login bem-sucedido, redirecione para a próxima atividade
                    val user = auth.currentUser
                    Toast.makeText(this, "Login bem-sucedido !!!", Toast.LENGTH_SHORT).show()
                    updateUI(user)
                    val i = Intent(this, LogadoMainActivity2::class.java)

                    i.putExtra("email", email)

                    startActivity(i)
                } else {
                    // Se o login falhar, exiba uma mensagem de erro
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    val snackbar = Snackbar.make(binding.root,"Falha na autenticação, verifique o email ou senha",Snackbar.LENGTH_SHORT)

                    val textView = snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                    textView.setTextColor(ContextCompat.getColor(this, R.color.orange))

                    snackbar.show()

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
