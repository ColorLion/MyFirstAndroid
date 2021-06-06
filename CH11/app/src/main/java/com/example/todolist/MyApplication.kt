package com.example.todolist

import android.app.Application
import io.realm.Realm

<<<<<<< HEAD
class MyApplication: Application(){
=======
class MyApplication : Application(){
>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
    override fun onCreate(){
        super.onCreate()
        Realm.init(this)
    }
}