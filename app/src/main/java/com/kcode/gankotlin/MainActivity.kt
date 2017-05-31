package com.kcode.gankotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.kcode.gankotlin.ui.adapter.MainAdapter
import com.kcode.gankotlin.ui.fragment.AndroidFragment
import com.kcode.gankotlin.ui.fragment.IOSFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragments = ArrayList<Fragment>()

        fragments.add(AndroidFragment())
        fragments.add(IOSFragment())

        viewPager.adapter = MainAdapter(fragments,supportFragmentManager)

        tabLayout.setupWithViewPager(viewPager)

    }
}
