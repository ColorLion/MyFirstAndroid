package com.example.mywebbrowser

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import kotlinx.android.extensions.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // web view 기본 설정
        webView.apply{
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }

        webView.loadUrl("https://www.piugame.com")

        // 검색 url 만들어봄
        var searchUrl: String? = null
        var checkHttpString = IntRange(0, 3)
        urlEditText.setOnEditorActionListener{_, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                if (urlEditText.text.toString().slice(checkHttpString) == "http"){
                    webView.loadUrl(urlEditText.text.toString())
                } else {
                    searchUrl = "https://" + urlEditText.text.toString()
                    webView.loadUrl(searchUrl)
                }
                true
            }else{
                false
            }
        }

        //context menu 등록
        registerForContextMenu(webView)
    }

    // 뒤로가기 재정의
    override fun onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack()
        }else{
            super.onBackPressed()
        }
    }

    // menu등록
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    // menu 기능 구현
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_piu, R.id.action_home -> {
                webView.loadUrl("https://www.piugame.com")
                return true
            }
            R.id.action_naver ->{
                webView.loadUrl("https://www.naver.com")
                return true
            }
            R.id.action_google -> {
                webView.loadUrl("https:www.google.com")
                return true
            }
            R.id.action_call -> {
                /*
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:010-2981-7549")
                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }*/
                makeCall("010-2981-7549")
                return true
            }
            R.id.action_send_text -> {
                sendSMS("010-2981-7549", webView.url)
                return true
            }
            R.id.action_email -> {
                email("test@naver.com", "좋은 사이트", webView.url)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item?.itemId){
            R.id.action_share -> {
                // 페이지 공유
                share(webView.url)
                return true
            }
            R.id.action_browser -> {
                //기본 웹 브라우저에서 열기
                browse(webView.url)
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}