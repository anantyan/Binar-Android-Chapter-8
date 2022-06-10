package id.anantyan.moviesapp.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersLocalRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val oAuth: GoogleSignInClient
) {
    fun getUsers() = auth.currentUser

    suspend fun signOutCredential() {
        auth.signOut()
        oAuth.signOut().await()
    }

    suspend fun signInCredential(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).await()
    }
}