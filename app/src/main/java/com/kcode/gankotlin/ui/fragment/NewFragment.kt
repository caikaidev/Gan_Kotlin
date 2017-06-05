package com.kcode.gankotlin.ui.fragment

import com.kcode.gankotlin.common.Type

/**
 * Created by caik on 2017/5/31.
 */
class NewFragment : ArticleFragment(){
    override fun getType(): String {
        return Type.all.name
    }
}