package com.maxpayneman.project_movie.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.maxpayneman.aulayt_8.R
import com.maxpayneman.aulayt_8.databinding.ActivityFilmeSelecionadoMainBinding
import com.maxpayneman.aulayt_8.databinding.ActivityFilmesSearchMainBinding

class FilmeSelecionadoMainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityFilmeSelecionadoMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmeSelecionadoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent

        val bundle = i.extras


        val nome = bundle?.getString("filmename")
        val imagemUrl = bundle?.getString("filmeimg")
        val descri = bundle?.getString("filmedesc")

        if (bundle != null && bundle.containsKey("filmeimg")) {

            binding.nomeFilme.text = nome

            binding.descricao.text = descri
            Glide.with(this).load(imagemUrl).into(binding.imageview)
        }

        binding.selecionarfilme.setOnClickListener{

            val i2 = Intent(applicationContext, MeusFilmesMainActivity::class.java)


            i2.putExtra("filmename", nome)
            i2.putExtra("filmeimg", imagemUrl)
            i2.putExtra("filmedesc", descri)

            startActivity(i2)

        }




    }
}