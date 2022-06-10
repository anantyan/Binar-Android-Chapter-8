package id.anantyan.moviesapp.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import id.anantyan.moviesapp.repository.UsersLocalRepository
import id.anantyan.moviesapp.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UsersLocalRepository
) : ViewModel() {
    private val _signInWithGoogle: MutableLiveData<Resource<Unit?>> = MutableLiveData()
    val signInWithGoogle: LiveData<Resource<Unit?>> = _signInWithGoogle
    fun signInWithGoogle(account: GoogleSignInAccount) = CoroutineScope(Dispatchers.Main).launch {
        try {
            val unit = repository.signInCredential(account)
            _signInWithGoogle.postValue(Resource.Success(unit))
        } catch (ex: Exception) {
            _signInWithGoogle.postValue(Resource.Error(null, "${ex.message}"))
        }
    }
}