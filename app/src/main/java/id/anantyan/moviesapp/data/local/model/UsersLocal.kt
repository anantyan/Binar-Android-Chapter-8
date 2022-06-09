package id.anantyan.moviesapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_users")
data class UsersLocal(
    @PrimaryKey(autoGenerate = true)
    val userId: Int? = null,
    @ColumnInfo(name = "fullname")
    val fullname: String? = null,
    @ColumnInfo(name = "username")
    val username: String? = null,
    @ColumnInfo(name = "email")
    val email: String? = null,
    @ColumnInfo(name = "password")
    val password: String? = null,
    @ColumnInfo(name = "photo")
    val photo: String? = null
)
