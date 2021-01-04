package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// build.gradle(module)에 id 'kotlin-android-extensions' 추가해야됨
import kotlinx.android.synthetic.main.activity_main.*
import android.preference.PreferenceManager
import android.content.SharedPreferences
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    private fun saveData(height: Int, weight: Int) {
        // 구버전 =  val pref = PreferenceManager.getDefaultSharedPreferences(this)
        // 신버전 - 둘 다 동작을 함, load를 언제하는지, save를 언제하는지 잘 알아두면 될듯
        val pref = this.getPreferences(0)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height).apply()
        editor.putInt("KEY_WEIGHT", weight) .apply()
    }

    private fun loadData(){
        // 구버전 = val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val pref = this.getPreferences(0)
        val height = pref.getInt("KEY_HEIGHT", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if (height != 0 && weight != 0){
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }

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
        /*
        //intent에 데이터 담기
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("weight", weightEditText.text.toString())
        intent.putExtra("height", heightEditText.text.toString())
        startActivity(intent)
        */

        // 이전 값 읽어오기
        loadData()

        //anko 라이브러리 적용한 코돌린의 액티비티 전환 코드, 안했으면 위 처럼 했어야 함
        resultButton.setOnClickListener{
            // 마지막에 입력한 내용 저장
            saveData(heightEditText.text.toString().toInt(), weightEditText.text.toString().toInt())
            
            startActivity<ResultActivity>(
                    "weight" to weightEditText.text.toString(),
                    "height" to heightEditText.text.toString()
            )
        }

    }
}