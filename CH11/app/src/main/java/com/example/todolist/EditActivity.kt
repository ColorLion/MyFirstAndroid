package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {
    val realm = Realm.getDefaultInstance()
    val calendar : Calendar = Calendar.getInstance()

    private fun insertTodo(){
        realm.beginTransaction()

        val newItem = realm.createObject<Todo>(nextId())
        newItem.title = todoEditText.text.toString()
        newItem.date = calendar.timeInMillis

        realm.commitTransaction()
        alert("내용이 추가됨 ㅋ"){
            yesButton{finish()}
        }.show()
    }

    // 다음 id를 반환
    private fun nextId(): Int{
        val maxId = realm.where<Todo>().max("id")
        if (maxId != null){
            return maxId.toInt() + 1
        }
        return 0
    }

    private fun updateTodo(id: Long){
        realm.beginTransaction()

        val updateItem = realm.where<Todo>().equalTo("id", id).findFirst()!!
        updateItem.title = todoEditText.text.toString()
        updateItem.date = calendar.timeInMillis

        realm.commitTransaction()
        
        alert("변경됨 ㅇㅇ"){
            yesButton { finish() }
        }.show()
    }

    private fun deleteTodo(id: Long){
        realm.beginTransaction()
        val deleteItem = realm.where<Todo>().equalTo("id", id).findFirst()!!

        deleteItem.deleteFromRealm()
        realm.commitTransaction()
        
        alert("내용 지움 ㅇㅇ"){
            yesButton { finish() }
        }.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val id = intent.getLongExtra("id", -1L)
        if (id == -1L){
            insertMode()
        }else{
            updateMode(id)
        }
        
        // 캘린더 뷰의 날짜를 선택했을 때 calendar 객체에 설정

        calendarView.setOnDateChangeListener{ view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }

    //추가 모드
    private fun insertMode(){
        deleteFab.visibility = View.GONE
        doneFab.setOnClickListener{
            insertTodo()
        }
    }

    //수정 모드
    private fun updateMode(id: Long){
        val todo = realm.where<Todo>().equalTo("id", id).findFirst()!!
        todoEditText.setText(todo.title)
        calendarView.date = todo.date

        doneFab.setOnClickListener {
            updateTodo(id)
        }

        deleteFab.setOnClickListener {
            deleteTodo(id)
        }
    }


    override fun onDestroy(){
        super.onDestroy()
        realm.close()
    }
}