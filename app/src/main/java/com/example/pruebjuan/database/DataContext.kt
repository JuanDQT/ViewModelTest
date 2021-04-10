package com.example.pruebjuan.database

class DataContext {
    companion object{
        fun getRepoUsers(): RepoUsers {
            return RepoUsers(MyDatabase.getDatabase())
        }
    }
}