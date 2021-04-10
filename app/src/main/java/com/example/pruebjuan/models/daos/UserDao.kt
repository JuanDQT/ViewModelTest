package com.example.pruebjuan.models.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.pruebjuan.models.User

@Dao
interface UserDao {

    @Query("select * from user")
    fun getAll(): List<User>

    @Insert(onConflict = IGNORE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("select * from user limit 1")
    fun getFirst(): User

    // Cuando se usa realmente?
    @Query("SELECT * FROM user")
    fun getAllLiveData(): LiveData<List<User>>

}