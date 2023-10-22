package com.maxpayneman.project_movie.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.maxpayneman.aulayt_8.R

class UserAtualMainActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_atual_main)

        startActivity(Intent(this, LoginMainActivity::class.java))

    }


    private fun meusFilmes(){
        val i = Intent(applicationContext, MeusFilmesMainActivity::class.java)
        startActivity(i)
        finish()

    }
    public override fun onStart() {
        super.onStart()
//        val currentUser = auth.currentUser
        val  usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null) {
            meusFilmes()

        }else{
            reload("deslogado")
        }
    }

    private fun reload(situacao: String) {
        val  usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (situacao.equals("deslogado")) {

            usuarioAtual?.reload()?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Usuario ${db.collection("Usuario").toString()} delogado com sucesso", Toast.LENGTH_SHORT).show()

                    updateUI(usuarioAtual)
                } else {

                    Toast.makeText(this, "Falha ao recarregar o usuário", Toast.LENGTH_SHORT).show()
                    updateUI(null)

                }
            }
        }else{

        }
    }
    private fun updateUI(user: FirebaseUser?) {
        // Atualize a interface do usuário conforme necessário
    }




    companion object {
        private const val TAG = "EmailPassword"
    }

}