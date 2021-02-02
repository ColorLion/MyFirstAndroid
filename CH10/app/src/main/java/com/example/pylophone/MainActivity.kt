package com.example.pylophone

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val soundPool = if(Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP){
        SoundPool.Builder().setMaxStreams(12).build()
    }else{
        SoundPool(12, AudioManager.STREAM_MUSIC, 0)
    }
    private var left = 1.0f
    private var right = 1.0f
    private var rate = 1.0f

    private val sounds = listOf(
        Pair(R.id.note1, R.raw.bb),        Pair(R.id.note2, R.raw.gachi),        Pair(R.id.note3, R.raw.hia),
        Pair(R.id.note4, R.raw.cheerup),        Pair(R.id.note5, R.raw.dd),        Pair(R.id.note6, R.raw.fist),
        Pair(R.id.note7, R.raw.ssg),        Pair(R.id.note8, R.raw.wdwd),        Pair(R.id.note9, R.raw.yeaha),
        Pair(R.id.note10, R.raw.crazy),        Pair(R.id.note11, R.raw.pooh),        Pair(R.id.note12, R.raw.gdr),
        Pair(R.id.note13, R.raw.bb),        Pair(R.id.note14, R.raw.ok),        Pair(R.id.note15, R.raw.hide),
        Pair(R.id.note16, R.raw.bbang),        Pair(R.id.note17, R.raw.gimochi),        Pair(R.id.note18, R.raw.shake),
        Pair(R.id.note19, R.raw.grb),        Pair(R.id.note20, R.raw.gs)
    )

    private fun tune(pitch: Pair<Int, Int>){
        val soundId = soundPool.load(this, pitch.second, 1)

        findViewById<TextView>(pitch.first).setOnClickListener{
            soundPool.play(soundId, left, right, 0, 0, rate)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        leftVol.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked){
                right = 0.3f
                leftUpText.setText("LEFT UP")
            } else {
                right = 1.0f
                leftUpText.setText("OFF")
            }
        }
        rightVol.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked){
                left = 0.3f
                rightUpText.setText("RIGHT UP")
            } else {
                left = 1.0f
                rightUpText.setText("OFF")
            }
        }
        slowRateSwitch.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked){
                rate = 0.8f
                slowRateText.setText("RATE x0.5")
            } else {
                rate = 1.0f
                rightUpText.setText("OFF")
            }
        }
        fastRateSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                rate = 1.2f
                fastRateText.setText("RATE x2")
            } else {
                rate = 1.0f
                fastRateText.setText("OFF")
            }
        }

        sounds.forEach{ tune(it)}
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}