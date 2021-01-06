package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    // fab 함수
    private var time = 0
    private var isRunning = false
    private var timerTask: Timer? = null
    // lab 타임 기록 메서드
    private var lab = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 시작 버튼
        fab.setOnClickListener{
            isRunning = !isRunning
            if(isRunning){
                start()
            }else{
                pause()
            }
        }
        
        // 기록 버튼
        labButton.setOnClickListener{
            recordLabTime()
        }
        
        // 리셋 버튼
        resetFab.setOnClickListener{
            reset()
        }
    }

    private fun start(){
        fab.setImageResource(R.drawable.ic_baseline_pause_24)

        timerTask = timer(period = 10){
            time++
            val sec = time / 100
            val milli = time % 100

            runOnUiThread(){
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }
    }

    private fun pause(){
        fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        timerTask?.cancel()
    }

    private fun recordLabTime(){
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lab LAB : ${lapTime / 100}.${lapTime % 100}"

        labLayout.addView(textView, 0)
        lab++
    }
    private fun reset(){
        timerTask?.cancel()

        // 모든 변수 초기화
        time = 0
        isRunning = false
        fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        secTextView.text = "0"
        milliTextView.text = "00"

        // 랩타임 제거
        labLayout.removeAllViews()
        lab = 1
    }
}