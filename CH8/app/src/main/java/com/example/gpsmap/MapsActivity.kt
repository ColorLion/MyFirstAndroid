package com.example.gpsmap

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    
    // 위치 정보를 주기적으로 얻는데 필요한 객체 선언
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: MyLocationCallBack
    
    //실행중 권한 요청 메서드 작성
    private val REQUEST_ACCESS_FINE_LOCATION = 1000

    // PolyLine 옵션
    private val polyLineOptions = PolylineOptions().width(5f).color(Color.GREEN)

    //인자 2개 받음, 두 함수는 인자가 없고 반환 값도 없음
    private fun permissionCheck(cancel: () -> Unit, ok: () -> Unit){
        //위치 권한이 잇는지 검사
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           //권한이 허용되지 않음
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                // 이전에 권한을 한번 거부한 적이 잇는 경우에 실행
                cancel()
            }else{
                // 권한 요청
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_ACCESS_FINE_LOCATION)
            }
        } else {
            // 권한을 실행 햇을 때 실행할 함수
            ok()
        }
    }
    
    // 권한이 필요한 이유를 설명하는 다이얼로그 표시 메서드 추가
    private fun showPermissionInfoDialog(){
        alert("현재 위치 정보를 얻으려면 위치 권한이 필요합니다", "권한이 필요한 이유"){
            yesButton{
                // 권한 요청
                ActivityCompat.requestPermissions(this@MapsActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_ACCESS_FINE_LOCATION)
            }
            noButton {  }
        }.show()
    }

    @SuppressLint("MissingPermission")
    private fun addLocationListener(){
        // 이거 쓰려면 권한 체크 해야되는듯
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    inner class MyLocationCallBack : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)

            val location = locationResult?.lastLocation
            location?.run{
                // 14 Lv로 확대하며 현재 위치로 카메라 이동
                val latLng = LatLng(latitude, longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))

                Log.d("MapsActivity", "위도: $latitude, 경도: $longitude")
                // PolyLine에 좌표 추가
                polyLineOptions.add(latLng)

                // 선 그리기
                mMap.addPolyline(polyLineOptions)
            }
        }
    }

    // 위치 정보 요청에 대한 세부 정보를 설정
    private fun locationInit(){
        fusedLocationProviderClient = FusedLocationProviderClient(this)

        locationCallback = MyLocationCallBack()

        locationRequest = LocationRequest()
        //GPS 우선
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        //업데이트 인터벌
        //위치 정보가 없을 땐 업데이트 안함
        //상황에 따라 짧아질 수 있음
        //다른 앱에서 짧은 인터벌로 위치 정보를 요청하면 짧아질 수 잇음
        locationRequest.interval = 10000
        //정확. 이것보다 짧은 업데이트는 하지 않음
        locationRequest.fastestInterval = 5000
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            REQUEST_ACCESS_FINE_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    //권한 허용됨
                    addLocationListener()
                }else{
                    //권한 거부
                    toast("권한이 거부되었어요 ㅋ")
                }
                return
            }
        }
    }

    private fun removeLocationListener(){
        // 현재 위치 요청을 삭제
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 화면 유지
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        // 세로 모드로 화면 고정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // SupportMapFragment를 가져와서 지도가 준비되면 알림을 받음
        setContentView(R.layout.activity_maps)
        
        // 프래그먼트 매니저로부터 SupportMapFragment를 얻음
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // 위치 정보 객체를 초기화
        locationInit()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        // 지도가 준비되면 GoogleMap 객체를 얻음
        mMap = googleMap

        //위도와 경도로 시드니의 쉬치를 정하고 구길 지도 객체에 마커를 추가하고 카메라 이동
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onResume() {
        super.onResume()
        
        // 권한 요청
        permissionCheck(cancel = {
            // 위치 정보가 필요한 이유 다이얼로그 표시
            showPermissionInfoDialog()
        }, ok ={
            // 현재 위치를 주기적으로 요청(권한 필요)
            addLocationListener()
        })
        
    }

    override fun onPause() {
        super.onPause()
        removeLocationListener()
    }

}