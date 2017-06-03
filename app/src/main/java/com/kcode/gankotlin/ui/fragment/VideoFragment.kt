package com.kcode.gankotlin.ui.fragment

/**
 * Created by caik on 2017/6/2.
 */
class VideoFragment :ArticleFragment(){
    override fun getType(): String {
        return "休息视频"
    }

    companion object{
        fun newInstance():VideoFragment {
            return VideoFragment()
        }
    }
}