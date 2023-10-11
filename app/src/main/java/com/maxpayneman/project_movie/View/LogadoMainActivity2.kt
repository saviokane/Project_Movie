package com.maxpayneman.project_movie.View

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.maxpayneman.aulayt_8.databinding.ActivityLogadoMain2Binding

class LogadoMainActivity2 : AppCompatActivity() {
    private lateinit var binding : ActivityLogadoMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogadoMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

    Handler(Looper.getMainLooper()).postDelayed({
        startActivity(Intent(this, FilmesSearchMainActivity::class.java));

    },2000)

    }
}