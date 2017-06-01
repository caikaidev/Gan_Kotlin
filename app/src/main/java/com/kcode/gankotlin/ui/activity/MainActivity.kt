package com.kcode.gankotlin.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.kcode.gankotlin.R
import com.kcode.gankotlin.ui.adapter.MainAdapter
import com.kcode.gankotlin.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragments = ArrayList<Fragment>()

        fragments.add(NewFragment())
        fragments.add(AndroidFragment())
        fragments.add(IOSFragment())
        fragments.add(WebFragment())
        fragments.add(WelfareFragment())

        viewPager.adapter = MainAdapter(fragments, supportFragmentManager)
        viewPager.offscreenPageLimit = 5

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.setCurrentItem(tab!!.position,false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })

    }
}
