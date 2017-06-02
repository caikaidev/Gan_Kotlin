package com.kcode.gankotlin.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kcode.gankotlin.R

/**
 * Created by caik on 2017/6/2.
 */
class HistoryFragment : Fragment() {

    companion object{
        fun newInstance():HistoryFragment{
            return HistoryFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_history, container, false)
    }
}