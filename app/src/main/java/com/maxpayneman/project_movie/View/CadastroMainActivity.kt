package com.maxpayneman.project_movie.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.maxpayneman.aulayt_8.databinding.ActivityCadastroMainBinding
import com.maxpayneman.project_movie.Model.Usuario
import com.maxpayneman.project_movie.ViewModel.UsuarioViewModel

class CadastroMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroMainBinding
    private lateinit var viewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)

        binding.buttonVoltar.setOnClickListener {
            finish()
        }

        binding.buttonRealizarCadastro.setOnClickListener {

             val usuario = Usuario()
             usuario.nome = binding.editNome.text.toString()
             usuario.senha = binding.editSenha.text.toString()
             usuario.email = binding.editUser.text.toString()
             usuario.idade = binding.editIdade.text.toString().toInt()

            if (usuario.nome.isEmpty() || usuario.senha.isEmpty() || usuario.email.isEmpty() || usuario.idade == 0) {
                Toast.makeText(this, "Preencha todos os campos !!!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.cadastrarUsuario(usuario,
                    onSuccess = {
                        Toast.makeText(baseContext, "Usuario Cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginMainActivity::class.java))
                        finish()
                    },
                    onFailure = { errorMessage ->
                        Toast.makeText(baseContext, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}
