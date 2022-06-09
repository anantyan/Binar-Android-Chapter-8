package id.anantyan.moviesapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.anantyan.moviesapp.data.api.MoviesApi
import id.anantyan.moviesapp.data.local.UsersDao
import id.anantyan.moviesapp.database.RoomDB
import id.anantyan.moviesapp.ui.profile.ProfileAdapter
import id.anantyan.moviesapp.utils.DataStoreManager
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
    fun provideDataStore(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @Singleton
    @Provides
    fun provideAdapterProfile(): ProfileAdapter {
        return ProfileAdapter()
    }

    @Provides
    fun provideUsersDao(roomDB: RoomDB): UsersDao {
        return roomDB.usersDao()
    }
}