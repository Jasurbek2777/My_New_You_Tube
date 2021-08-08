package com.example.mynewyoutube.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mynewyoutube.fragments.HomeInnerFragment

class ViewPagerAdapter(fm: FragmentActivity, position: Int) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = 5
    override fun createFragment(position: Int): Fragment {
        return HomeInnerFragment.newInstance()
    }
}