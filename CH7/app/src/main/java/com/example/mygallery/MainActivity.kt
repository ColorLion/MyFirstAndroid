package com.example.mygallery

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {
    private val REQUEST_READ_EXTERNAL_STORAGE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 외부 저장소 권한 확인
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            // 권한이 허용 되지 않음
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                //이전에 이미 권한이 거부되어 있을때
                alert("사진 정보를 얻으려면 외부 저장소 권한이 필요합니다.", "권한이 필요한 이유"){
                    yesButton {
                        // 권한 요청
                        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
                    }
                    noButton {  }
                }.show()
            } else {
                // 권한 요청
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
            }
        } else {
            // 이미 권한이 허용됨
            getAllPhotos()
        }


    }

    private fun getAllPhotos(){
        //모든 사진 정보 가져오기
        var cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            null,
                            null,
                            null,
                            // desc 한칸 띄워서 써야함 ㅋㅋㅋ
                            MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC")

        if (cursor != null) {
            while (cursor.moveToNext()){
                //사진 경로 uri 가져오기
                val uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                Log.d("MainActivity", uri)
            }
            // 커서는 다쓰면 꼭 close 해줘야 한다 안그럼 메모리 누수난다
            cursor.close()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_READ_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // 권한 허용됨
                    getAllPhotos()
                } else {
                    // 권한 거부
                    toast("권한 거부 ㅅㄱ")
                }
                return
            }
        }
    }
}