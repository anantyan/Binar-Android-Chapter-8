package id.anantyan.moviesapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.anantyan.moviesapp.database.RoomDB
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext
        context: Context
    ) : RoomDB {
        return Room.databaseBuilder(
            context.applicationContext,
            RoomDB::class.java,
            "db_moviesapp"
        ).fallbackToDestructiveMigration().build()
    }
}