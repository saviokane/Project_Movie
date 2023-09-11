package com.maxpayneman.project_movie.View

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.maxpayneman.aulayt_8.databinding.ActivitySplashScreeanMainBinding

class SplashScreeanMainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivitySplashScreeanMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashScreeanMainBinding.inflate(layoutInflater);
        super.onCreate(savedInstanceState)
        setContentView(binding.root);

        Handler(Looper.getMainLooper()).postDelayed({
        startActivity(Intent(this, InicioMainActivity::class.java));
            finish();
        }, 2000);

    }
}