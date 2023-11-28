import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserAtualViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    fun verificarUsuarioAtual(): FirebaseUser? {
        return auth.currentUser
    }
}
