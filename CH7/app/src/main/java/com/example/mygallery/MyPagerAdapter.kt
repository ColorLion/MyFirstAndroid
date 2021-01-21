package com.example.mygallery

import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    // 뷰 페이저가 표시할 프래그먼트 목록
    private val items = ArrayList<Fragment>()

    //position 위치의 프래그먼트
    override fun createFragment(position: Int): Fragment {
        return items[position]
    }

    //아이템 개수
    override fun getItemCount(): Int {
        return items.size
    }

    //아이템 갱신
    fun updateFragments(items : List<Fragment>){
        this.items.addAll(items)
    }
}

/* 이것을 viewPager2에 맞게 업데이트 해보도록 합시다
class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    // 뷰 페이저가 표시할 프래그먼트 목록
    private val items = ArrayList<Fragment>()
    
    //position 위치의 프래그먼트
    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    //아이템 개수
    override fun getCount(): Int {
        return items.size
    }

    //아이템 갱신
    fun updateFragments(items : List<Fragment>){
        this.items.addAll(items)
    }
}
 */