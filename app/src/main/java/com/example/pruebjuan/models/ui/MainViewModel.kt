package com.example.pruebjuan.models.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avisual.tasklistapp.common.ScopeViewModel
import com.example.pruebjuan.database.RepoUsers
import com.example.pruebjuan.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repoUsers: RepoUsers) : ScopeViewModel() {

    fun saveUser(user: User) {
        launch(Dispatchers.IO) {
            repoUsers.save(user)
        }
    }

    private suspend fun getAllusers() {
        launch(Dispatchers.IO) {
            repoUsers.getAll()
        }
    }

}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repoUsers: RepoUsers) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(repoUsers) as T
}