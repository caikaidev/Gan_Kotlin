package com.kcode.gankotlin.ui.fragment

import com.kcode.gankotlin.common.Type

/**
 * Created by caik on 2017/6/2.
 */
class ExpandFragment:ArticleFragment() {

    companion object{
        fun newInstance():ExpandFragment {
            return ExpandFragment()
        }
    }

    override fun getType(): String {
        return Type.拓展资源.name
    }
}