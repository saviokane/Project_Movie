package com.maxpayneman.project_movie.Model

class UsuarioModel {

    private  var _nome: String = "";
    private var _user: String = "";
    private  var _idade: Int = 0;
    private  var _senha: String = "";


    var senha: String
        get(){
            return _senha;
        }set(value){
            _senha = value;
        }

    var user: String
        get(){
            return _user;
        }set(value){
            _user = value;
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



    constructor(){
        this._idade = 0;
        this._nome = "";

    }
constructor(   nome : String , senha: String, idade : Int, user: String){
    this._idade = idade;
    this._nome = nome;
    this._senha = senha;
    this._user = user;
}


}
