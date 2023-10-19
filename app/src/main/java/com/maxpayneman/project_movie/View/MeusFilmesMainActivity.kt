    package com.maxpayneman.project_movie.View

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.ArrayAdapter
    import com.maxpayneman.aulayt_8.R
    import com.maxpayneman.aulayt_8.databinding.ActivityMeusFilmesMainBinding
    import com.maxpayneman.project_movie.Model.FilmeLista

    class MeusFilmesMainActivity : AppCompatActivity() {
    
        private var  minhalista = mutableListOf<FilmeLista>()

        private lateinit var  binding: ActivityMeusFilmesMainBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMeusFilmesMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, minhalista)

            binding.minhalistafilm.adapter = adapter

            val i = intent

            val bundle = i.extras

            val nome = bundle?.getString("filmename").toString().trim()
            val image = bundle?.getString("filmeimg").toString().trim()
            val descricao = bundle?.getString("filmedesc").toString().trim()

            binding.valor.text = nome

            minhalista.add(FilmeLista(nome))


            adapter.notifyDataSetChanged()
        }
    }