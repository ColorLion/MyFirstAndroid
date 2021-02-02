package com.example.pylophone

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val soundPool = if(Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP){
        SoundPool.Builder().setMaxStreams(12).build()
    }else{
        SoundPool(12, AudioManager.STREAM_MUSIC, 0)
    }

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
            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sounds.forEach{ tune(it)}
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}