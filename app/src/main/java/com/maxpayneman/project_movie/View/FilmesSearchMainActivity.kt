package com.maxpayneman.project_movie.View

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.maxpayneman.aulayt_8.databinding.ActivityFilmesSearchMainBinding
import com.maxpayneman.project_movie.Model.Filme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class FilmesSearchMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmesSearchMainBinding


    private var listaFilmes = ArrayList<Filme>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmesSearchMainBinding.inflate(layoutInflater)
        setContentView(binding.root)






        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaFilmes)

        binding.listView.adapter = adapter;

        binding.buscarFilme.setOnClickListener {
            listaFilmes.clear()
            CoroutineScope(Dispatchers.IO).launch {

                val nameFilme = binding.editFilmeName.text.toString()

                val client = OkHttpClient()

                val request = Request.Builder()
                    .url("https://api.themoviedb.org/3/search/movie?query=${nameFilme}&language=pt-BR&page=1&include_adult=false")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader(
                        "Authorization",
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhMWYwOTM0YTNlM2M2NDk3ZDM2NjdhZjc5ZWQ1MDI4YiIsInN1YiI6IjY1MWNiNDQ1OTY3Y2M3MzQyNWYxZjYxMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.LS9fUPTf5XHelcwXoM_6PeD92B9htZJluem8u8QBCtI"
                    )
                    .build()

                val response = client.newCall(request).execute()

                val responseData = response.body?.string()


                responseData?.let {
                    val jsonObject = JSONObject(it)
                    val resultsArray = jsonObject.getJSONArray("results")

                    for (i in 0 until resultsArray.length()) {
                        val filme = resultsArray.getJSONObject(i)
                        val titulo = filme.getString("title")
                        val ano = filme.getString("release_date")
                        val descricao = filme.getString("overview")
                        val id = filme.getInt("id")

                        val detalhesRequest = Request.Builder()
                            .url("https://api.themoviedb.org/3/movie/$id?language=pt-BR")
                            .get()
                            .addHeader("accept", "application/json")
                            .addHeader(
                                "Authorization",
                                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhMWYwOTM0YTNlM2M2NDk3ZDM2NjdhZjc5ZWQ1MDI4YiIsInN1YiI6IjY1MWNiNDQ1OTY3Y2M3MzQyNWYxZjYxMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.LS9fUPTf5XHelcwXoM_6PeD92B9htZJluem8u8QBCtI"
                            )
                            .build()


                        val detalhesResponse = client.newCall(detalhesRequest).execute()
                        val detalhesData = detalhesResponse.body?.string()

                        detalhesData?.let {
                            val detalhesJson = JSONObject(it)
                            val imagemPath = detalhesJson.getString("poster_path")
                            val imagemUrl = "https://image.tmdb.org/t/p/w500$imagemPath"

                            // Adiciona o filme à lista com o URL da imagem
                            listaFilmes.add(Filme(titulo, ano, imagemUrl))
                        }
                        runOnUiThread {
                            adapter.notifyDataSetChanged()
                        }
                    }

                }
            }
        }
    }
}