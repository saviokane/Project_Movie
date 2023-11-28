package com.maxpayneman.project_movie.Model

data class Filme(var id: Int, var nome : String , var Data : String , var imageUrl : String , var descricao : String) {

    override fun toString(): String {
        return "$nome"+" || "+"$Data"
    }
}