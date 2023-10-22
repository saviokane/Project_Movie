package com.maxpayneman.project_movie.View

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.maxpayneman.aulayt_8.databinding.ActivityCadastroMainBinding


class CadastroMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroMainBinding
    private lateinit var auth: FirebaseAuth
    private  val db = FirebaseFirestore.getInstance()
    private val user = FirebaseAuth.getInstance().currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.buttonVoltar.setOnClickListener {
            finish()

        }

        binding.buttonRealizarCadastro.setOnClickListener {

            val nome = binding.editNome.text.toString()
            val senha = binding.editSenha.text.toString()
            val email = binding.editUser.text.toString()
            val idade = binding.editIdade.text.toString().toInt()

            if (nome.isEmpty() || senha.isEmpty() || email.isEmpty() || idade == 0) {
                Toast.makeText(this, "Preencha todos os campos !!!", Toast.LENGTH_SHORT).show()
            } else {

                auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(baseContext,"Usuario Cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

                        updateUI(null)
                        val user = auth.currentUser
                        updateUI(user)
                        val uid = user?.uid

                        val users = hashMapOf(
                            "nome" to "${nome}",
                            "idade" to "${idade}",
                            "email" to "${email}",
                            "senha" to "${senha}"
                        )
                        uid?.let{
                            db.collection("Usuarios").document(it).collection("users").document("Usuario")
                                .set(users).addOnCompleteListener {
                                    Log.d("db", "Sucesso ao realizar cadastro!")
                                }
                        startActivity(Intent(this, LoginMainActivity::class.java))
                    }
                }

                }
            }
        }
    }




    private fun updateUI(user: FirebaseUser?) {
        // Atualize a interface do usuário conforme necessário
    }
}
