    package com.maxpayneman.project_movie.View

    import MeuAdaptador
    import android.content.Intent
    import android.graphics.Color
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.util.Log
    import android.view.Menu
    import android.widget.ArrayAdapter
    import android.widget.Toast
    import androidx.core.graphics.toColorInt
    import com.google.firebase.auth.FirebaseAuth
    import com.google.firebase.firestore.*
    import com.google.firebase.firestore.FirebaseFirestore
    import com.maxpayneman.aulayt_8.R
    import com.maxpayneman.aulayt_8.databinding.ActivityMeusFilmesMainBinding
    import com.maxpayneman.project_movie.Model.FilmeLista
    import java.util.*
    import com.google.firebase.firestore.DocumentSnapshot
    import com.google.firebase.firestore.EventListener
    import com.google.firebase.firestore.FirebaseFirestoreException
    import com.maxpayneman.project_movie.Model.Usuario
    import com.maxpayneman.project_movie.Model.UsuarioModel


    class MeusFilmesMainActivity : AppCompatActivity() {

        private var  minhalista = mutableListOf<FilmeLista>()
        private val user = FirebaseAuth.getInstance().currentUser
        private val db = FirebaseFirestore.getInstance()
        private lateinit var  binding: ActivityMeusFilmesMainBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMeusFilmesMainBinding.inflate(layoutInflater)
            setContentView(binding.root)




            val meuAdaptador = MeuAdaptador(applicationContext, minhalista)
            binding.minhalistafilm.adapter = meuAdaptador


            //lista de imagens do meu banco salvos !
            user?.uid?.let { uid ->
                db.collection("FilmesUsuario").document(uid).collection("MeusFilmes")
                    .get()
                    .addOnSuccessListener { querySnapshot  ->
                        for (document in querySnapshot) {
                            val nomeFilme = document.getString("nome").toString()
                            val imgURl = document.getString("imagUrl").toString()
                            minhalista.add(FilmeLista(nomeFilme,imgURl))
                            meuAdaptador.notifyDataSetChanged()
                        }

                    }
                    .addOnFailureListener { exception ->
                        Log.d("db", "Erro ao obter o documento: $exception")
                    }
            }

            val uid = user?.uid
            binding.valor.text = "Carregando..." // Pode ser um texto temporário enquanto os dados são carregados



//aqui a região onde em tempo real e exibido o nick do usuario no app
            user?.uid?.let { uid ->
                db.collection("Usuarios").document(uid).collection("users").document("Usuario")
                    .get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            val nomeUsuario = document.getString("nome")
                            binding.valor.text = nomeUsuario
                        } else {
                            Log.d("db", "Documento não encontrado")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d("db", "Erro ao obter o documento: $exception")
                    }
            }





//Local para deslogar o usuario !
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
