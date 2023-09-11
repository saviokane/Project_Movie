package com.maxpayneman.project_movie.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maxpayneman.aulayt_8.databinding.ActivityInicioMainBinding

class InicioMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInicioMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInicioMainBinding.inflate(layoutInflater);
        super.onCreate(savedInstanceState)
        setContentView(binding.root);

        binding.buttonCadastro.setOnClickListener {

        startActivity(Intent(this, CadastroMainActivity::class.java));

        }

        binding.buttonLogar.setOnClickListener {

            startActivity(Intent(this, LoginMainActivity::class.java));

        }

    }
}