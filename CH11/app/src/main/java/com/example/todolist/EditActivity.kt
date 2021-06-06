package com.example.todolist

import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
<<<<<<< HEAD
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
=======
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
>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
        }.show()
    }

    // 다음 id를 반환
<<<<<<< HEAD
    private fun nextId(): Int {
=======
    private fun nextId(): Int{
>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
        val maxId = realm.where<Todo>().max("id")
        if (maxId != null){
            return maxId.toInt() + 1
        }
        return 0
    }

<<<<<<< HEAD
    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateTodo(id: Long){
        realm.beginTransaction()

        // == not null
=======
    private fun updateTodo(id: Long){
        realm.beginTransaction()

>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
        val updateItem = realm.where<Todo>().equalTo("id", id).findFirst()!!
        updateItem.title = todoEditText.text.toString()
        updateItem.date = calendar.timeInMillis

        realm.commitTransaction()
<<<<<<< HEAD

        //다이얼로그 표시
        alert("내용이 변경됨 ㅇㅇ"){
=======
        
        alert("변경됨 ㅇㅇ"){
>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
            yesButton { finish() }
        }.show()
    }

    private fun deleteTodo(id: Long){
        realm.beginTransaction()
<<<<<<< HEAD

        val deleteItem =  realm.where<Todo>().equalTo("id", id).findFirst()!!

        //삭제할 객체
        deleteItem.deleteFromRealm()
        realm.commitTransaction()

        alert("삭제됨 ㅇㅇ"){
=======
        val deleteItem = realm.where<Todo>().equalTo("id", id).findFirst()!!

        deleteItem.deleteFromRealm()
        realm.commitTransaction()
        
        alert("내용 지움 ㅇㅇ"){
>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
            yesButton { finish() }
        }.show()
    }

<<<<<<< HEAD
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        
        //추가/수정 분기 처리
        // 1. 업데이트 조건
=======
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
        val id = intent.getLongExtra("id", -1L)
        if (id == -1L){
            insertMode()
        }else{
            updateMode(id)
        }
<<<<<<< HEAD

        // 2. 캘린더 뷰의 날짜를 선택했을 때 Calendar 객체에 설정
=======
        
        // 캘린더 뷰의 날짜를 선택했을 때 calendar 객체에 설정

>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
        calendarView.setOnDateChangeListener{ view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }

<<<<<<< HEAD
    // 추가 모드 초기화
    @RequiresApi(Build.VERSION_CODES.N)
    private fun insertMode(){
        // 삭제 버튼 감추기
        deleteFab.visibility = View.GONE

        //완료 버튼을 클릭하면 추가
=======
    //추가 모드
    private fun insertMode(){
        deleteFab.visibility = View.GONE
>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
        doneFab.setOnClickListener{
            insertTodo()
        }
    }

<<<<<<< HEAD
    // 수정 모드 초기화
    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateMode(id: Long){
        // id에 해당하는 객체를 화면에 표시
=======
    //수정 모드
    private fun updateMode(id: Long){
>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
        val todo = realm.where<Todo>().equalTo("id", id).findFirst()!!
        todoEditText.setText(todo.title)
        calendarView.date = todo.date

<<<<<<< HEAD
        //완료 버튼을 클릭하면 수정
=======
>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
        doneFab.setOnClickListener {
            updateTodo(id)
        }

<<<<<<< HEAD
        //삭제 버튼을 클릭하면 삭제
        deleteFab.setOnClickListener{
=======
        deleteFab.setOnClickListener {
>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
            deleteTodo(id)
        }
    }

<<<<<<< HEAD
    override fun onDestroy() {
=======

    override fun onDestroy(){
>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
        super.onDestroy()
        realm.close()
    }
}