package com.maxpayneman.project_movie.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maxpayneman.aulayt_8.databinding.ActivityCadastroMainBinding
import com.maxpayneman.project_movie.Controller.UsuarioController

class CadastroMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCadastroMainBinding.inflate(layoutInflater);
        super.onCreate(savedInstanceState)
        setContentView(binding.root);



        val usuario  = UsuarioController.getInstance();





//        usuario.Registrar("maylon", "153", 1, "1");






        binding.buttonRealizarCadastro.setOnClickListener {

            var nome = binding.editNome.text.toString();
            var senha = binding.editSenha.text.toString();
            var usuarios = binding.editUser.text.toString()
            var idade = binding.editIdade.text.toString().toInt();

            usuario.Registrar(nome,senha, idade, usuarios);

            startActivity(Intent(this, InicioMainActivity::class.java))

            Toast.makeText(this, "Cadastro realizado com sucesso ", Toast.LENGTH_SHORT).show()
            finish()//apartir daqui ele limpa o cache e retorna a margin 0
        }

        binding.buttonVoltar.setOnClickListener {

            finish();

        }

    }
}