package com.maxpayneman.project_movie.View

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maxpayneman.aulayt_8.databinding.ActivityMainBinding
import com.maxpayneman.project_movie.Controller.UsuarioController


class LoginMainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater);
        super.onCreate(savedInstanceState)
        setContentView(binding.root);

        var usuario  = UsuarioController.getInstance();
        val context : Context = this;
//
//        usuario.Registrar("1", "2",10, "sd");


        binding.buttonLogar.setOnClickListener {
            val user = binding.user.text.toString();
            val senha = binding.senha.text.toString();

            binding.textTeste2.text =  usuario.Mostrar();
            usuario.Validação(context,user,senha);

        }

        binding.buttonVoltar.setOnClickListener {
            finish();
        }




    }
}

