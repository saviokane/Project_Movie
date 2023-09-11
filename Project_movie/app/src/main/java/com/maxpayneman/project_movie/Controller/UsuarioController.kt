package com.maxpayneman.project_movie.Controller

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.maxpayneman.project_movie.Model.UsuarioModel
import com.maxpayneman.project_movie.View.LogadoMainActivity2



class UsuarioController private constructor() {
    private var listaUsuarioModel : MutableList<UsuarioModel> = mutableListOf()

    companion object{
        private var instance: UsuarioController? = null

        fun getInstance(): UsuarioController {
            if(instance == null){
                instance = UsuarioController()
            }
            return instance!!
        }
    }
    // Aqui foi criado uma instancia para manter a lista armazenada e atualizada por onde quer que
    // o aplicativo passe em relação aos dados !

fun Mostrar(): String{
        val resultado = StringBuilder();
        var id : Int =0;

        for(usuario : UsuarioModel in listaUsuarioModel){

            resultado.append("id: ${id + 1} Nome: ${usuario.nome} Idade: ${usuario.idade}  Nome de usuario: ${usuario.user}\n" );
            id++;
        }
        return resultado.toString();
    }

    fun Registrar(nome: String , senha: String, idade: Int, user : String){
        var usuarioModel = UsuarioModel(nome,senha, idade, user)
        listaUsuarioModel.add(usuarioModel);
    }

    //Ridiculo kotlim exige que faça em ordem se não, não vai ser realizado de forma correta
    fun Validação(context : Context , usuario2: String ,senha: String){

        for (usuario : UsuarioModel in listaUsuarioModel){
            if (  usuario2 == usuario.user && senha == usuario.senha){
                val intent = Intent(context, LogadoMainActivity2::class.java)
                context.startActivity(intent)
                return
            }
        }
        Toast.makeText(context, "Usuario ou senha incorretos! ", Toast.LENGTH_LONG).show()
    }


    }

