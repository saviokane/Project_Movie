package com.maxpayneman.project_movie.Model

class Usuario {

    private  var _nome: String = "";
    private  var _idade: Int = 0;
    private var _email: String = "";
    private  var _senha: String = "";


    var senha: String
        get(){
            return _senha;
        }set(value){
            _senha = value;
        }

    var email: String
        get(){
            return _email;
        }set(value){
            _email = value;
        }



        var idade:Int
        get(){
            return _idade
        }set(value) {
            _idade = value;
        }

    var nome:String
        get(){
        println("obtendo nome")
        return _nome
    }
        set (value){
            _nome = value;
        }






}
