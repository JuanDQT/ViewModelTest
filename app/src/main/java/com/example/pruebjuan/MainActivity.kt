package com.example.pruebjuan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pruebjuan.database.DataContext
import com.example.pruebjuan.database.MyDatabase
import com.example.pruebjuan.database.RepoUsers
import com.example.pruebjuan.databinding.ActivityMainBinding
import com.example.pruebjuan.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.withTestContext
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var repoUsers: RepoUsers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instanceDB()

        binding.btnAdd.setOnClickListener { addUser() }
        binding.btnAll.setOnClickListener { getAll() }
        binding.btnDelete.setOnClickListener { deleteUser() }

    }

    private fun instanceDB() {
        val database = MyDatabase.getDatabase()
        repoUsers = RepoUsers(database)
    }

    private fun addUser() {
        val randomNumber = (0..10).random()

        val user: User = User(name = "Marcos ${randomNumber}", lastName = "Mrtitos${randomNumber}")
        DataContext.getRepoUsers().saveWithOutCR(user = user)
//        repoUsers.saveWithOutCR(user = user)
    }

    // Crear view models
    // Crear calse unica instancia repo
    fun deleteUser() {
        val items = DataContext.getRepoUsers().getAllWithOutCR()
        if (items.size > 0) {
            DataContext.getRepoUsers().deleteWithOutCR(DataContext.getRepoUsers().getFirstWithOutCR())
//            repoUsers.deleteWithOutCR(repoUsers.getFirstWithOutCR())
            Log.i("Total", "${items}")
        }
    }

    fun getAll() {
        val items = DataContext.getRepoUsers().getAllWithOutCR()
//        val items = repoUsers.getAllWithOutCR()
        Log.i("Total", "${items}")

    }

}