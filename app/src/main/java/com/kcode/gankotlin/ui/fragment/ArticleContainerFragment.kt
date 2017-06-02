package com.kcode.gankotlin.ui.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kcode.gankotlin.R
import com.kcode.gankotlin.ui.adapter.MainAdapter
import kotlinx.android.synthetic.main.fragment_article_container.*

/**
 * Created by caik on 2017/6/1.
 */
class ArticleContainerFragment :Fragment(){

    companion object {
        fun newInstance():ArticleContainerFragment {
            return ArticleContainerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_article_container, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments = ArrayList<Fragment>()

        fragments.add(NewFragment())
        fragments.add(AndroidFragment())
        fragments.add(IOSFragment())
        fragments.add(WebFragment())

        viewPager.adapter = MainAdapter(fragments, childFragmentManager)
        viewPager.offscreenPageLimit = 4

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