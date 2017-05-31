package com.kcode.gankotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.kcode.gankotlin.ui.adapter.MainAdapter
import com.kcode.gankotlin.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(R.string.app_name)

        val fragments = ArrayList<Fragment>()

        fragments.add(NewFragment())
        fragments.add(AndroidFragment())
        fragments.add(IOSFragment())
        fragments.add(WebFragment())
        fragments.add(WelfareFragment())

        viewPager.adapter = MainAdapter(fragments,supportFragmentManager)

        tabLayout.setupWithViewPager(viewPager)

    }
}
