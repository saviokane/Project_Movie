package com.maxpayneman.project_movie.View

import LoginViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import com.maxpayneman.aulayt_8.R
import com.maxpayneman.aulayt_8.databinding.ActivityMainBinding


class LoginMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val loginViewModel: LoginViewModel  by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonLogar.setOnClickListener {
            val email = binding.email.text.toString()
            val senha = binding.senha.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty()) {
                loginViewModel.signIn(email,senha,onSuccess = { user ->
                        Toast.makeText(this, "Login bem-sucedido !!!", Toast.LENGTH_SHORT).show()
                        updateUI(user)
                        val i = Intent(this, MeusFilmesMainActivity::class.java)
                        i.putExtra("email", email)
                        startActivity(i)
                        finish()
                    },
                    onFailure = { errorMessage ->
                        val snackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_SHORT)
                        val textView = snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                        textView.setTextColor(ContextCompat.getColor(this, R.color.orange))
                        snackbar.show()
                        updateUI(null)
                    }
                )
            } else {
                Toast.makeText(this, "Preencha o usuário e senha", Toast.LENGTH_SHORT).show()
            }
        }

        binding.criarConta.setOnClickListener {
            startActivity(Intent(this, CadastroMainActivity::class.java))
        }
    }

    private fun updateUI(user: FirebaseUser?) {
    }
}
