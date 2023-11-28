import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FilmeSelecionadoViewModel : ViewModel() {
    private val user = FirebaseAuth.getInstance().currentUser
    private val db = FirebaseFirestore.getInstance()
    private val _toastMessage = MutableLiveData<String>()




    fun adicionarFilme(nome: String, descricao: String, imagemUrl: String) {
        user?.uid?.let { uid ->
            val filmeRef = db.collection("FilmesUsuario").document(uid).collection("MeusFilmes").document()

            val MeusFilmes = hashMapOf(
                "id" to filmeRef.id,
                "nome" to nome,
                "descricao" to descricao,
                "imagUrl" to imagemUrl
            )


            filmeRef
                .set(MeusFilmes)
                .addOnCompleteListener {
                    _toastMessage.value = "Filme Adicionado!!"
                }
                .addOnFailureListener { e ->
                    _toastMessage.value = "Erro ao adicionar filme: $e"
                }
        }

    }

    fun deletarFilme(uidFilme: String) {
        user?.uid?.let { uidUsuario ->
            val filmeRef = db.collection("FilmesUsuario").document(uidUsuario)
                .collection("MeusFilmes").document(uidFilme)

            filmeRef
                .delete()
                .addOnCompleteListener {
                    _toastMessage.value = "Filme deletado com sucesso!!"

                }
                .addOnFailureListener { e ->
                    _toastMessage.value = "Erro ao deletar filme: $e"
                }
        }
    }
}
