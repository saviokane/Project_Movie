package com.maxpayneman.project_movie.Model

class Filme(var nome : String , var Data : String , var imageUrl : String) {

    override fun toString(): String {
        return "$nome $Data"
    }
}