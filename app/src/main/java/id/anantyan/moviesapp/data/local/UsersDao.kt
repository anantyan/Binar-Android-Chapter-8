package id.anantyan.moviesapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.anantyan.moviesapp.data.local.model.UsersLocal

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun register(item: UsersLocal): Long

    @Query("SELECT * FROM tbl_users WHERE email=:email AND password=:password")
    suspend fun login(email: String?, password: String?): UsersLocal?

    @Query("SELECT * FROM tbl_users WHERE username=:username OR email=:email")
    suspend fun checkAccount(username: String?, email: String?): UsersLocal?

    @Query("SELECT * FROM tbl_users WHERE userId=:userId")
    suspend fun showAccount(userId: Int?): UsersLocal?
}