package com.maxpayneman.project_movie.View

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

        val nome = i.extras?.getString("filmename")

        if (bundle != null && bundle.containsKey("filmeimg")) {

            val imagemUrl = bundle.getString("filmeimg")
            val descri = bundle.getString("filmedesc")

            binding.nomeFilme.text = nome

            binding.descricao.text = descri
            Glide.with(this).load(imagemUrl).into(binding.imageview)
        }







    }
}