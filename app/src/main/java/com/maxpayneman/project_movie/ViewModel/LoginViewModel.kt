import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    fun signIn(email: String, password: String, onSuccess: (FirebaseUser) -> Unit, onFailure: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess.invoke(auth.currentUser!!)
                } else {
                    onFailure.invoke("Falha na autenticação, verifique o email ou senha")
                }
            }
    }
}
