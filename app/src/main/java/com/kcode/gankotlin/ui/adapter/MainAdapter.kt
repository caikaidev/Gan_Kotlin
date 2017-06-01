package com.kcode.gankotlin.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import java.util.*

/**
 * Created by caik on 2017/5/31.
 */
class MainAdapter(var data:List<Fragment> = ArrayList(),fm:FragmentManager) : FragmentStatePagerAdapter(fm) {

    val titles = arrayOf("最新","安卓","iOS","前端","福利")

    override fun getItem(position: Int): Fragment {
        return data[position]
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}