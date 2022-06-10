package id.anantyan.moviesapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.anantyan.moviesapp.R
import id.anantyan.moviesapp.model.ProfileLocal
import id.anantyan.moviesapp.repository.UsersLocalRepository
import id.anantyan.moviesapp.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UsersLocalRepository
) : ViewModel() {

    private val _signOut: MutableLiveData<Resource<Unit?>> = MutableLiveData()
    val signOut: LiveData<Resource<Unit?>> = _signOut

    private val _showAccount: MutableLiveData<Resource<List<ProfileLocal>>> = MutableLiveData()
    val showAccount: LiveData<Resource<List<ProfileLocal>>> = _showAccount

    fun signOut() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val unit = repository.signOutCredential()
            _signOut.postValue(Resource.Success(unit))
        } catch (ex: Exception) {
            _signOut.postValue(Resource.Error(null, "${ex.message}"))
        }
    }

    fun showAccount() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = repository.getUsers()
            response?.let {
                val list = listOf(
                    ProfileLocal(R.drawable.ic_outline_person_pin_24, "Name", it.displayName),
                    ProfileLocal(R.drawable.ic_outline_alternate_email_24, "Email", it.email)
                )
                _showAccount.postValue(Resource.Success(list))
            }
        } catch (ex: Exception) {
            _showAccount.postValue(
                ex.message?.let { Resource.Error(code = null, message = it) }
            )
        }
    }
}