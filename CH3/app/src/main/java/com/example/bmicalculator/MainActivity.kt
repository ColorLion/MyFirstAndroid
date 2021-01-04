package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// build.gradle(module)에 id 'kotlin-android-extensions' 추가해야됨
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        resultButton.setOnClickListener {
            // 결과 버튼이 클릭되면 할 일을 작성하는 부분
            // 액티비티를 전환할 때마다 항상 인텐트 객체를 생성하고 startActivity() 메서드를 호출해야 한다 -> 타이핑 양이 상당함
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
        */
        //anko 라이브러리 적용한 코돌린의 액티비티 전환 코드, 안했으면 위 처럼 했어야 함
        resultButton.setOnClickListener{
            startActivity<ResultActivity>()
        }

    }
}