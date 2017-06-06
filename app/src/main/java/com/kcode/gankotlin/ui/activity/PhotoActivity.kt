package com.kcode.gankotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.kcode.gankotlin.R
import kotlinx.android.synthetic.main.activity_photo.*
import kotlinx.android.synthetic.main.common_toolbar.*

/**
 * Created by caik on 2017/6/5.
 */
class PhotoActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        initToolbar()

        val url = intent.getStringExtra("url")
        if (TextUtils.isEmpty(url)) {
            finish()
            return
        }

        Glide
                .with(this)
                .load(url)
                .into(photoView)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.title = ""
        actionBar.setDisplayHomeAsUpEnabled(true)
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
}