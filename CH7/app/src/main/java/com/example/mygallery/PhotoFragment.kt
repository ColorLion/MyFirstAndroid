package com.example.mygallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_photo.*

// 컴파일 할 때 결정되는 상수
private const val ARG_URI = "uri"

class PhotoFragment : Fragment() {
    private var uri: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // fragment 생성 시 onCreate() 호출되면 ARG_URI에 저장된 값을 얻어 변수에 저장
        arguments?.let {
            uri = it.getString(ARG_URI)
        }
    }

    // 뷰가 생성된 직후에 호출됨
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    //
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).load(uri).into(imageView)
    }

    // newInstance 메서드를 이용해 프래그먼트를 생성하고 인자로 uri값을 전달
    // companion = static멤버 변수나 함수가 없기 때문에 클래스 인스턴스 없이 어떤 클래스 내부에 접근하고 싶다면 object를 선언할 때 붙여주자
    companion object {
        @JvmStatic
        fun newInstance(uri: String) =
            PhotoFragment().apply { arguments = Bundle().apply { putString(ARG_URI, uri) } }
    }
}