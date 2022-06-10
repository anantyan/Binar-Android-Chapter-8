package id.anantyan.moviesapp.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.anantyan.moviesapp.data.api.MoviesApi
import id.anantyan.moviesapp.ui.profile.ProfileAdapter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAdapterProfile(): ProfileAdapter {
        return ProfileAdapter()
    }

    @Singleton
    @Provides
    fun provideFirebaseAuth(
        @ApplicationContext context: Context
    ): FirebaseAuth {
        FirebaseApp.initializeApp(context)
        return Firebase.auth
    }

    @Singleton
    @Provides
    fun provideGoogleSignClient(
        @ApplicationContext context: Context,
        gso: GoogleSignInOptions
    ): GoogleSignInClient {
        return GoogleSignIn.getClient(context, gso)
    }
}