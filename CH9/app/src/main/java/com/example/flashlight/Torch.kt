package com.example.flashlight

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

class Torch(context : Context) {
    // 카메라를 켜고 끄기 위한 id를 저장
    private var cameraId: String? = null
    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    init{
        cameraId = getCameraId()
    }

    fun flashOn(){
        cameraId?.let { cameraManager.setTorchMode(it, true) }
    }

    fun flashOff(){
        cameraId?.let{ cameraManager.setTorchMode(it, false) }
    }

    private fun getCameraId(): String?{
        // 카메라 id를 얻어 렌즈의 위치를 파악하고 플래시 여부를 확인
        val cameraIds = cameraManager.cameraIdList
        for (id in cameraIds){
            val info = cameraManager.getCameraCharacteristics(id)
            val flashAvailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
            val lensFacing = info.get(CameraCharacteristics.LENS_FACING)
            if(flashAvailable != null && flashAvailable && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK){
                return id
            }
        }

        return null
    }
}