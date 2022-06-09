package id.anantyan.moviesapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.anantyan.moviesapp.data.local.UsersDao
import id.anantyan.moviesapp.data.local.model.UsersLocal

@Database(entities = [
    UsersLocal::class
], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}