package com.example.tiltsensor

import TiltView
import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : AppCompatActivity(), SensorEventListener{
    //sensor 정의, 지연된 초기화
    private val sensorManager by lazy{
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    //TiltView를 화면에 배치
    private lateinit var tiltView: TiltView

    override fun onCreate(savedInstanceState: Bundle?) {
        // 가로 모드로 고정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        // 화면이 꺼지지 않게 하기
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tiltView = TiltView(this)
        setContentView(tiltView)
    }

    // 액티비티가 동작할때만 센서가 동작시키도록
    // 일반적으로 센서 사용 등록은 엑티비티의 onResume() 메서드에서 수행
    override fun onResume(){
        super.onResume()
        // 리스너를 this로 정의했기 때문에 MainActivity가 SensorEventListener를 구현하도록 추가
        // MainActivity에 없으니 추가해 ㅈ도록 하자
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        // 센서 값이 변경되면 호출됨
        // values[0] : x축 값 : 위로 기울이면 -10 ~ 0, 아래로 기울이면 0 ~ 10
        // values[1] : y축 값 : 왼쪽으로 기울이면 -10 ~ 0, 오른쪽으로 기울이면 0 ~ 10
        // values[2] : z축 값 : 미사용
        event?.let {
            // 안드로이드 스튜디오 로그창에 띄울 때 주로 사용함
            Log.d("MainActivity", "onSensorChanged: x : ${event.values[0]}, y : ${event.values[1]}, z : ${event.values[2]}")

            tiltView.onSensorEvent(event)
        }
    }

    override fun onPause(){
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}
