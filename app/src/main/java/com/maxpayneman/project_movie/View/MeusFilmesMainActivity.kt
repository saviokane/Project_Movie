    package com.maxpayneman.project_movie.View


    import ItemClickListener
    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.util.Log
    import android.view.Menu
    import android.widget.Toast
    import androidx.lifecycle.Observer
    import androidx.lifecycle.ViewModelProvider
    import com.google.firebase.auth.FirebaseAuth
    import com.google.firebase.firestore.FirebaseFirestore
    import com.maxpayneman.aulayt_8.R
    import com.maxpayneman.aulayt_8.databinding.ActivityMeusFilmesMainBinding
    import com.maxpayneman.project_movie.Model.Adaptador.MeuAdaptador
    import com.maxpayneman.project_movie.Model.FilmeLista
    import com.maxpayneman.project_movie.ViewModel.MeusFilmesViewModel


    class MeusFilmesMainActivity : AppCompatActivity() {

        private val user = FirebaseAuth.getInstance().currentUser
        private val db = FirebaseFirestore.getInstance()
        private lateinit var binding: ActivityMeusFilmesMainBinding
        private val viewModel: MeusFilmesViewModel by lazy {
            ViewModelProvider(this).get(MeusFilmesViewModel::class.java)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMeusFilmesMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val meuAdaptador = MeuAdaptador(
                applicationContext,
                mutableListOf(),
                viewModel,
                object : ItemClickListener {
                    override fun onItemClicked(filme: FilmeLista) {
                        val nome = filme.nome
                        val img = filme.imgUrl
                        val descricao = filme.sinopse

                        val i = Intent(applicationContext, FilmeSelecionadoMainActivity::class.java)
                        i.putExtra("filmename", nome)
                        i.putExtra("filmeimg", img)
                        i.putExtra("filmedesc", descricao)
                        startActivity(i)
                    }
                }
            )
            binding.minhalistafilm.adapter = meuAdaptador


            viewModel.minhaLista.observe(this, Observer { listaFilmes ->
                meuAdaptador.updateLista(listaFilmes)
            })

            viewModel.nomeUsuario.observe(this, Observer { nomeUsuario ->
                binding.valor.text = nomeUsuario
            })





            binding.toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.deslogar -> {
                        user?.uid?.let { uid ->
                            db.collection("Usuarios").document(uid).collection("users").document("Usuario")
                                .get()
                                .addOnSuccessListener { document ->
                                    if (document != null) {
                                        val nomeUsuario = document.getString("nome")
                                        Toast.makeText(applicationContext, "Usuario $nomeUsuario Deslogado com sucesso!", Toast.LENGTH_SHORT).show()
                                        FirebaseAuth.getInstance().signOut()
                                        startActivity(Intent(this, LoginMainActivity::class.java))
                                        finish()

                                    } else {
                                        Log.d("db", "Documento não encontrado")
                                    }
                                }
                                .addOnFailureListener { exception ->
                                    Log.d("db", "Erro ao obter o documento: $exception")
                                }
                        }
                    true
                    }
                    R.id.buscar_filme -> {

                        //binding.valor.setTextColor(Color.RED) // muda a cor pelo back
                        startActivity(Intent(applicationContext, FilmesSearchMainActivity::class.java))
                        true
                    }

                    else -> false

                }
            }
        }
        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        }


    }
