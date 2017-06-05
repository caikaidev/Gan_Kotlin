package com.kcode.gankotlin.ui.fragment

import com.kcode.gankotlin.common.Type

/**
 * Created by caik on 2017/5/31.
 */
class AndroidFragment : ArticleFragment(){

    companion object{
        fun newInstance():AndroidFragment {
            return AndroidFragment()
        }
    }

    override fun getType(): String {
        return Type.Android.name
    }

}