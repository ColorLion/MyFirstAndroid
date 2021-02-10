package com.example.todolist

import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton
import java.util.*
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    val realm = Realm.getDefaultInstance()
    @RequiresApi(Build.VERSION_CODES.N)
    val calendar: Calendar = Calendar.getInstance()

    @RequiresApi(Build.VERSION_CODES.N)
    private fun insertTodo(){
        // 트랜젝션 시작
        realm.beginTransaction()

        //새 객체 생성
        val newItem = realm.createObject<Todo>(nextId())
        // 값 설정
        newItem.title = todoEditText.text.toString()
        newItem.date = calendar.timeInMillis

        // 트랜젝션 종료 반영
        realm.commitTransaction()
        
        // 다이얼로그 표시
        alert("내용 추가됨 ㅇㅇ"){
            yesButton {  finish()  }
        }.show()
    }

    // 다음 id를 반환
    private fun nextId(): Int {
        val maxId = realm.where<Todo>().max("id")
        if (maxId != null){
            return maxId.toInt() + 1
        }
        return 0
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateTodo(id: Long){
        realm.beginTransaction()

        // == not null
        val updateItem = realm.where<Todo>().equalTo("id", id).findFirst()!!
        updateItem.title = todoEditText.text.toString()
        updateItem.date = calendar.timeInMillis

        realm.commitTransaction()

        //다이얼로그 표시
        alert("내용이 변경됨 ㅇㅇ"){
            yesButton { finish() }
        }.show()
    }

    private fun deleteTodo(id: Long){
        realm.beginTransaction()

        val deleteItem =  realm.where<Todo>().equalTo("id", id).findFirst()!!

        //삭제할 객체
        deleteItem.deleteFromRealm()
        realm.commitTransaction()

        alert("삭제됨 ㅇㅇ"){
            yesButton { finish() }
        }.show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        
        //추가/수정 분기 처리
        // 1. 업데이트 조건
        val id = intent.getLongExtra("id", -1L)
        if (id == -1L){
            insertMode()
        }else{
            updateMode(id)
        }

        // 2. 캘린더 뷰의 날짜를 선택했을 때 Calendar 객체에 설정
        calendarView.setOnDateChangeListener{ view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }

    // 추가 모드 초기화
    @RequiresApi(Build.VERSION_CODES.N)
    private fun insertMode(){
        // 삭제 버튼 감추기
        deleteFab.visibility = View.GONE

        //완료 버튼을 클릭하면 추가
        doneFab.setOnClickListener{
            insertTodo()
        }
    }

    // 수정 모드 초기화
    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateMode(id: Long){
        // id에 해당하는 객체를 화면에 표시
        val todo = realm.where<Todo>().equalTo("id", id).findFirst()!!
        todoEditText.setText(todo.title)
        calendarView.date = todo.date

        //완료 버튼을 클릭하면 수정
        doneFab.setOnClickListener {
            updateTodo(id)
        }

        //삭제 버튼을 클릭하면 삭제
        deleteFab.setOnClickListener{
            deleteTodo(id)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}