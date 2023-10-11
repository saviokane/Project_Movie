package com.maxpayneman.project_movie.View

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.maxpayneman.aulayt_8.R
import com.maxpayneman.aulayt_8.databinding.ActivitySplashScreeanMainBinding

class SplashScreeanMainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivitySplashScreeanMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashScreeanMainBinding.inflate(layoutInflater);
        super.onCreate(savedInstanceState)
        setContentView(binding.root);

        val mediaPLayer = MediaPlayer.create(applicationContext, R.raw.toque)
        mediaPLayer.start()
        Handler(Looper.getMainLooper()).postDelayed({

            startActivity(Intent(this, LoginMainActivity::class.java));
            finish();
        }, 3000);

    }
}