package com.example.pruebjuan.models.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import com.example.pruebjuan.database.DataContext
import com.example.pruebjuan.database.MyDatabase
import com.example.pruebjuan.database.RepoUsers
import com.example.pruebjuan.databinding.ActivityMainBinding
import com.example.pruebjuan.models.User

class MainActivity : AppCompatActivity() {

    private lateinit var repoUsers: RepoUsers

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = buildViewModel()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // optional on future case
        buildDependencies()

        // events
        binding.btnAdd.setOnClickListener {
            val randomNumber = (0..10).random()
            val user = User(name = "Marcos ${randomNumber}", lastName = "Mrtitos${randomNumber}")
//            val task = Task(0, title, description)
            viewModel.saveUser(user)
        }

        binding.btnAll.setOnClickListener {
            //val items = repoUsers.getAll()
//        val items = repoUsers.getAllWithOutCR()
            Log.i("Total", "${items}")

        }
    }



    private fun buildDependencies() {
        val database = MyDatabase.getDatabase()
        repoUsers = RepoUsers(database)
    }


    private fun buildViewModel(): MainViewModel {
        val factory = MainViewModelFactory(repoUsers)
        return ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }




}