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

  private var lastIndex = -1
  private var lastFragment: Fragment? = null
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

  private fun changeTab(position: Int) {

    if (lastIndex == position) {
      return
    }

    lastIndex = position

    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()

    lastFragment?.let {
      fragmentTransaction.hide(it)
    }

    when (position) {
      0 -> {

        var articleContainerFragment = fragmentManager.findFragmentByTag(
            ArticleContainerFragment::class.java.simpleName) as ArticleContainerFragment?

        if (articleContainerFragment == null) {
          articleContainerFragment = ArticleContainerFragment.newInstance()
          fragmentTransaction.add(R.id.container, articleContainerFragment,
              ArticleContainerFragment::class.java.simpleName)
        } else {
          fragmentTransaction.show(articleContainerFragment)
        }
        lastFragment = articleContainerFragment
      }
      1 -> {
        var girlFragment = fragmentManager.findFragmentByTag(
            WelfareFragment::class.java.simpleName) as WelfareFragment?

        if (girlFragment == null) {
          girlFragment = WelfareFragment.newInstance()
          fragmentTransaction.add(R.id.container, girlFragment,
              WelfareFragment::class.java.simpleName)
        } else {
          fragmentTransaction.show(girlFragment)
        }

        lastFragment = girlFragment
      }
      2 -> {

        var historyFragment = fragmentManager.findFragmentByTag(
            HistoryFragment::class.java.simpleName) as HistoryFragment?

        if (historyFragment == null) {
          historyFragment = HistoryFragment.newInstance()
          fragmentTransaction.add(R.id.container, historyFragment,
              HistoryFragment::class.java.simpleName)
        } else {
          fragmentTransaction.show(historyFragment)
        }

        lastFragment = historyFragment

      }
    }

    fragmentTransaction.commit()
  }
}
