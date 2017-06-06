package com.kcode.gankotlin.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

/**
 * Created by caik on 2017/6/6.
 */
class ProgressFragment :DialogFragment(){

    companion object{
        fun newInstance(): ProgressFragment{
            return ProgressFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        return ProgressBar(activity)
    }
}