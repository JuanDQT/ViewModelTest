[1mdiff --git a/app/src/main/java/com/example/pruebjuan/common/Scope.kt b/app/src/main/java/com/example/pruebjuan/common/Scope.kt[m
[1mindex a29e5ef..d337247 100644[m
[1m--- a/app/src/main/java/com/example/pruebjuan/common/Scope.kt[m
[1m+++ b/app/src/main/java/com/example/pruebjuan/common/Scope.kt[m
[36m@@ -1,5 +1,3 @@[m
[31m-package com.avisual.tasklistapp.common[m
[31m-[m
 import kotlinx.coroutines.CoroutineScope[m
 import kotlinx.coroutines.Dispatchers[m
 import kotlinx.coroutines.Job[m
[1mdiff --git a/app/src/main/java/com/example/pruebjuan/common/ScopeViewModel.kt b/app/src/main/java/com/example/pruebjuan/common/ScopeViewModel.kt[m
[1mindex 5d7df1a..cc2bf92 100644[m
[1m--- a/app/src/main/java/com/example/pruebjuan/common/ScopeViewModel.kt[m
[1m+++ b/app/src/main/java/com/example/pruebjuan/common/ScopeViewModel.kt[m
[36m@@ -1,8 +1,5 @@[m
[31m-package com.avisual.tasklistapp.common[m
[31m-[m
 import androidx.annotation.CallSuper[m
 import androidx.lifecycle.ViewModel[m
[31m-import com.avisual.tasklistapp.common.Scope[m
 [m
 abstract class ScopeViewModel : ViewModel(), Scope by Scope.Impl() {[m
 [m
[1mdiff --git a/app/src/main/java/com/example/pruebjuan/database/RepoUsers.kt b/app/src/main/java/com/example/pruebjuan/database/RepoUsers.kt[m
[1mindex 36287dc..321e785 100644[m
[1m--- a/app/src/main/java/com/example/pruebjuan/database/RepoUsers.kt[m
[1m+++ b/app/src/main/java/com/example/pruebjuan/database/RepoUsers.kt[m
[36m@@ -4,6 +4,7 @@[m [mimport androidx.lifecycle.LiveData[m
 import com.example.pruebjuan.models.User[m
 import com.example.pruebjuan.models.daos.UserDao[m
 import kotlinx.coroutines.Dispatchers[m
[32m+[m[32mimport kotlinx.coroutines.delay[m
 import kotlinx.coroutines.withContext[m
 [m
 class RepoUsers(database: MyDatabase) {[m
[36m@@ -31,6 +32,7 @@[m [mclass RepoUsers(database: MyDatabase) {[m
 */[m
 [m
     suspend fun getAll() = withContext(Dispatchers.IO) {[m
[32m+[m[32m        delay(5000)[m
         userDao.getAll()[m
     }[m
 }[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/java/com/example/pruebjuan/models/ui/MainActivity.kt b/app/src/main/java/com/example/pruebjuan/models/ui/MainActivity.kt[m
[1mindex f468cf1..aa4eef0 100644[m
[1m--- a/app/src/main/java/com/example/pruebjuan/models/ui/MainActivity.kt[m
[1m+++ b/app/src/main/java/com/example/pruebjuan/models/ui/MainActivity.kt[m
[36m@@ -20,12 +20,12 @@[m [mclass MainActivity : AppCompatActivity() {[m
 [m
     override fun onCreate(savedInstanceState: Bundle?) {[m
         super.onCreate(savedInstanceState)[m
[31m-        viewModel = buildViewModel()[m
         binding = ActivityMainBinding.inflate(layoutInflater)[m
         setContentView(binding.root)[m
 [m
         // optional on future case[m
         buildDependencies()[m
[32m+[m[32m        viewModel = buildViewModel()[m
 [m
         // events[m
         binding.btnAdd.setOnClickListener {[m
[36m@@ -36,10 +36,8 @@[m [mclass MainActivity : AppCompatActivity() {[m
         }[m
 [m
         binding.btnAll.setOnClickListener {[m
[31m-            //val items = repoUsers.getAll()[m
[31m-//        val items = repoUsers.getAllWithOutCR()[m
[31m-            Log.i("Total", "${items}")[m
[31m-[m
[32m+[m[32m            viewModel.printAllItems()[m
[32m+[m[32m            print("contnamos")[m
         }[m
     }[m
 [m
[1mdiff --git a/app/src/main/java/com/example/pruebjuan/models/ui/MainViewModel.kt b/app/src/main/java/com/example/pruebjuan/models/ui/MainViewModel.kt[m
[1mindex 3cb1d78..ad898a6 100644[m
[1m--- a/app/src/main/java/com/example/pruebjuan/models/ui/MainViewModel.kt[m
[1m+++ b/app/src/main/java/com/example/pruebjuan/models/ui/MainViewModel.kt[m
[36m@@ -1,8 +1,9 @@[m
 package com.example.pruebjuan.models.ui[m
 [m
[32m+[m[32mimport ScopeViewModel[m
 import androidx.lifecycle.ViewModel[m
 import androidx.lifecycle.ViewModelProvider[m
[31m-import com.avisual.tasklistapp.common.ScopeViewModel[m
[32m+[m[32mimport com.example.pruebjuan.dat