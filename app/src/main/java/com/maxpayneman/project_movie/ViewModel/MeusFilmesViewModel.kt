package com.maxpayneman.project_movie.ViewModel

import FilmeSelecionadoViewModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.maxpayneman.project_movie.Model.FilmeLista

class MeusFilmesViewModel : ViewModel() {
    private val _minhaLista = MutableLiveData<List<FilmeLista>>()
    val minhaLista: LiveData<List<FilmeLista>> get() = _minhaLista
    private val _nomeUsuario = MutableLiveData<String>()
    val nomeUsuario: LiveData<String> get() = _nomeUsuario
    val selectFilme= FilmeSelecionadoViewModel()
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    init {
        carregarMinhaLista()
        carregarNomeUsuario()
    }

    private fun carregarMinhaLista() {
        auth.currentUser?.uid?.let { uid ->
            db.collection("FilmesUsuario").document(uid).collection("MeusFilmes")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val listaFilmes = mutableListOf<FilmeLista>()
                    for (document in querySnapshot) {
                        val nomeFilme = document.getString("nome").toString()
                        val imgUrl = document.getString("imagUrl").toString()
                        val descrition = document.getString("descricao").toString()
                        val id = document.getString("id").toString()
                        listaFilmes.add(FilmeLista(nomeFilme, imgUrl,descrition, id))
                    }
                    _minhaLista.postValue(listaFilmes)
                }
                .addOnFailureListener { exception ->
                    Log.d("db", "Erro ao obter a lista de filmes: $exception")
                }
        }
    }

    private fun carregarNomeUsuario() {
        auth.currentUser?.uid?.let { uid ->
            db.collection("Usuarios").document(uid).collection("users").document("Usuario")
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val nomeUsuario = document.getString("nome")
                        _nomeUsuario.postValue(nomeUsuario)
                    } else {
                        Log.d("db", "Documento não encontrado")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("db", "Erro ao obter o nome do usuário: $exception")
                }
        }
    }


    fun deletarFilme(id: String){
        selectFilme.deletarFilme(id)

    }

}
