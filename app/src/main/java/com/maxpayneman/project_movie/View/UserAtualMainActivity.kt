package com.maxpayneman.project_movie.View

import UserAtualViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.maxpayneman.aulayt_8.R

class UserAtualMainActivity : AppCompatActivity() {
    private val viewModel: UserAtualViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_atual_main)
        verificarUsuarioAtual()
    }

    private fun verificarUsuarioAtual() {
        val usuarioAtual = viewModel.verificarUsuarioAtual()

        if (usuarioAtual != null) {
            meusFilmes()
        } else {
            startActivity(Intent(this,LoginMainActivity::class.java))
            finish()
        }
    }

    private fun meusFilmes() {
        val i = Intent(applicationContext, MeusFilmesMainActivity::class.java)
        startActivity(i)
        finish()
    }

}