package com.example.pruebjuan.database

import androidx.lifecycle.LiveData
import com.example.pruebjuan.models.User
import com.example.pruebjuan.models.daos.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoUsers(database: MyDatabase) {

    private val TAG = "RepoUser"

    private var userDao: UserDao = database.userDao()

    suspend fun save(user: User) = withContext(Dispatchers.IO) {
        userDao.insert(user)
    }

    suspend fun delete(user: User) = withContext(Dispatchers.IO) {
        userDao.delete(user)
    }

    suspend fun update(user: User) = withContext(Dispatchers.IO) {
        userDao.update(user)
    }

    fun getAllLiveData() : LiveData<List<User>> {
        return userDao.getAllLiveData()
    }

    suspend fun getAll() = withContext(Dispatchers.IO) {
        userDao.getAll()
    }

    fun saveWithOutCR(user: User) {
        userDao.insert(user)
    }

    fun getAllWithOutCR(): List<User> {
        return userDao.getAll()
    }

    fun deleteWithOutCR(user: User) {
        userDao.delete(user)
    }

    fun getFirstWithOutCR(): User {
        return userDao.getFirst()
    }

}