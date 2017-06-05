package com.kcode.gankotlin.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.kcode.gankotlin.R
import com.kcode.gankotlin.ui.fragment.ArticleContainerFragment
import com.kcode.gankotlin.ui.fragment.HistoryFragment
import com.kcode.gankotlin.ui.fragment.WelfareFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastIndex = -1
    var lastFragment:Fragment? = null
    var articleContainerFragment:ArticleContainerFragment? = null
    var historyFragment:HistoryFragment? = null
    var girlFragment:WelfareFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_recommend -> {
                    changeTab(0)
                }
                R.id.action_girl -> {
                    changeTab(1)
                }
                R.id.action_history -> {
                    changeTab(2)
                }
            }

            true
        }

        //set default fragment
        changeTab(0)

    }

    fun changeTab(position: Int) {

        if (lastIndex == position ) {
            return
        }

        lastIndex = position

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (lastFragment != null) {
            fragmentTransaction.hide(lastFragment)
        }

        when (position) {
            0 -> {

                articleContainerFragment = fragmentManager.findFragmentByTag(ArticleContainerFragment::class.java.simpleName) as ArticleContainerFragment?

                if (articleContainerFragment == null) {
                    articleContainerFragment = ArticleContainerFragment.newInstance()
                    fragmentTransaction.add(R.id.container,articleContainerFragment,ArticleContainerFragment::class.java.simpleName)
                }else{
                    fragmentTransaction.show(articleContainerFragment)
                }

                lastFragment = articleContainerFragment
            }
            1 -> {
                girlFragment = fragmentManager.findFragmentByTag(WelfareFragment::class.java.simpleName) as WelfareFragment?

                if (girlFragment == null) {
                    girlFragment = WelfareFragment.newInstance()
                    fragmentTransaction.add(R.id.container,girlFragment,WelfareFragment::class.java.simpleName)
                }else{
                    fragmentTransaction.show(girlFragment)
                }

                lastFragment = girlFragment
            }
            2 -> {

                historyFragment = fragmentManager.findFragmentByTag(HistoryFragment::class.java.simpleName) as HistoryFragment?

                if (historyFragment == null) {
                    historyFragment = HistoryFragment.newInstance()
                    fragmentTransaction.add(R.id.container,historyFragment,HistoryFragment::class.java.simpleName)
                }else{
                    fragmentTransaction.show(historyFragment)
                }

                lastFragment = historyFragment

            }
        }

        fragmentTransaction.commit()
    }
}
