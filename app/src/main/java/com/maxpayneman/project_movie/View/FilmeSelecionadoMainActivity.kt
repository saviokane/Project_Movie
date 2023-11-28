package com.maxpayneman.project_movie.View

import FilmeSelecionadoViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.maxpayneman.aulayt_8.databinding.ActivityFilmeSelecionadoMainBinding
import com.maxpayneman.project_movie.Model.Filme
import com.maxpayneman.project_movie.Model.FilmeDatabaseHelper


class FilmeSelecionadoMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmeSelecionadoMainBinding
    private val viewModel: FilmeSelecionadoViewModel by viewModels()
    private lateinit var dbsql: FilmeDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmeSelecionadoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbsql = FilmeDatabaseHelper(this)

        val i = intent
        val bundle = i.extras

        if (bundle != null && bundle.containsKey("filmeimg")) {
            val nome = bundle.getString("filmename")
            val imagemUrl = bundle.getString("filmeimg")
            val descri = bundle.getString("filmedesc")
            val data = bundle.getString("filmedata")

            if (nome != null && imagemUrl != null && descri != null) {
                binding.nomeFilme.text = nome
                binding.descricaoFilme.text = descri
                Glide.with(this).load(imagemUrl).into(binding.imageview)

                binding.selecionarfilme.setOnClickListener {
                    viewModel.adicionarFilme(nome, descri, imagemUrl)
                    var filme = Filme(0, "$nome", "$data", "$imagemUrl", "$descri")
                    dbsql.adicionarFilme(filme)
                    Toast.makeText(this, "Filme adicionado com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                    startActivity(Intent(applicationContext, MeusFilmesMainActivity::class.java))
                }
            } else {
                Toast.makeText(this, "Informações do filme são nulas", Toast.LENGTH_SHORT).show()
            }

            binding.back.setOnClickListener {
                finish()
            }

        }

    }

}
