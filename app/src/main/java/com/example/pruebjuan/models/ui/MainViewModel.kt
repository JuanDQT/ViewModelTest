package com.example.pruebjuan.models.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pruebjuan.common.ScopeViewModel
import com.example.pruebjuan.database.DataContext
import com.example.pruebjuan.database.RepoUsers
import com.example.pruebjuan.models.User
import kotlinx.coroutines.*

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

    // TODO: En que casos usar globalscope? servicios?
    fun doSomething() {
        // Main o IO si se quiere hacer en paralelo o no
        launch(Dispatchers.Main) {
            val numero =  withContext(Dispatchers.IO) {getValorDesconocido()}
            Log.e("", "1. NO ESPERAMOS")
        }
    }

/*
    // Funciona
    fun doSomething() {
        launch(Dispatchers.IO) {
            val numero = withContext(Dispatchers.IO) {getValorDesconocido()}
            Log.e("", "1. NO ESPERAMOS")
        }
    }
*/

    // No deberia ejecutar uno a uno?...
    fun printAllItems() {
        launch() {
            print("kaka")
            print("kaka")
            DataContext.getRepoUsers().getAll()
            DataContext.getRepoUsers().getAll()
            DataContext.getRepoUsers().getAll()
            print("kaka")

//            val items = DataContext.getRepoUsers().getAll()
//            print("tenemos: ${items.count()}")
        }
    }

    private suspend fun getValorDesconocido() {
        launch {
            Thread.sleep(5000)
            Log.e("", "acabamos getvalor")
            5
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repoUsers: RepoUsers) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(repoUsers) as T
}