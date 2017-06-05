package com.kcode.gankotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.kcode.gankotlin.R
import com.kcode.gankotlin.ui.fragment.RecommendFragment
import kotlinx.android.synthetic.main.activity_history_detail.*

/**
 * Created by caik on 2017/6/5.
 */
class HistoryDetailActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_detail)

        val date = intent.getStringExtra("date")
        initToolbar(date)
        addContainer(date)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun addContainer(date:String) {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container,RecommendFragment.newInstance(date))
                .commit()
    }

    private fun initToolbar(title:String) {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }


}