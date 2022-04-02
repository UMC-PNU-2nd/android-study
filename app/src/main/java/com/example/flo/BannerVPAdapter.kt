package com.example.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BannerVPAdapter(fragment:Fragment) :FragmentStateAdapter(fragment) {

    private val fragmentlist : ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int = fragmentlist.size

    override fun createFragment(position: Int): Fragment = fragmentlist[position]

    // 처음 fragment에는 아무것도 없기 때문에 추가해줘야함
    fun addFragment(fragment: Fragment){
        fragmentlist.add(fragment)
        notifyItemInserted(fragmentlist.size-1) //VP에게 새로운 값이 추가됨을 알려줌
    }
}