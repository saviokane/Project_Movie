package com.maxpayneman.project_movie.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.maxpayneman.aulayt_8.R
import com.maxpayneman.aulayt_8.databinding.ActivityFilmeSelecionadoMainBinding
import com.maxpayneman.aulayt_8.databinding.ActivityFilmesSearchMainBinding

class FilmeSelecionadoMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmeSelecionadoMainBinding
    private val user = FirebaseAuth.getInstance().currentUser
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmeSelecionadoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent

        val bundle = i.extras
        val uid = user?.uid

        val nome = bundle?.getString("filmename")
        val imagemUrl = bundle?.getString("filmeimg")
        val descri = bundle?.getString("filmedesc")

        if (bundle != null && bundle.containsKey("filmeimg")) {

            binding.nomeFilme.text = nome

            binding.descricao.text = descri
            Glide.with(this).load(imagemUrl).into(binding.imageview)
        }

        binding.selecionarfilme.setOnClickListener {

            val i2 = Intent(applicationContext, MeusFilmesMainActivity::class.java)

            user?.uid?.let { uid ->
                val filmeRef = db.collection("FilmesUsuario").document(uid).collection("MeusFilmes").document()

                val MeusFilmes = hashMapOf(
                    "id" to filmeRef.id, // Use o ID gerado pelo Firestore como o ID do filme
                    "nome" to "$nome",
                    "descricao" to "$descri",
                    "imagUrl" to "$imagemUrl"
                )

                filmeRef
                    .set(MeusFilmes)
                    .addOnCompleteListener {
                        Log.d("db", "Filme Adicionado")
                    }
                    .addOnFailureListener { e ->
                        Log.e("db", "Erro ao adicionar filme: $e")
                    }
            }

                startActivity(i2)

            }


        }
    }

