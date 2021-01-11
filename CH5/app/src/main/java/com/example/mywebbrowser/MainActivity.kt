package com.example.mywebbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import kotlinx.android.extensions.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // web view 기본 설정
        webView.apply{
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }

        webView.loadUrl("http://www.piugame.com/piu.xx/")
        
        //keyboard 정의
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
    }
}