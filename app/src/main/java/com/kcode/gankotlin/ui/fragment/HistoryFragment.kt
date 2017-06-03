package com.kcode.gankotlin.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kcode.gankotlin.R
import com.kcode.gankotlin.net.Api
import com.kcode.gankotlin.repository.PublishedDate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by caik on 2017/6/2.
 */
class HistoryFragment : Fragment() {

    val TAG = HistoryFragment::class.java.simpleName
    val DATE_LINE = 30
    val dates:MutableList<com.kcode.gankotlin.repository.Date> = arrayListOf()

    companion object{
        fun newInstance():HistoryFragment{
            return HistoryFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPublishedDate()
    }

    fun loadPublishedDate() {
        val api = Api.Factory.create()
        api.getPublishedDate()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result -> parsePublishedDateResult(result)
                })
    }

    private fun parsePublishedDateResult(result: PublishedDate?) {
        Log.d(TAG,result.toString())
        if (result!!.error) {
            return
        }

        result!!.results.filter {

            //只展示近一个月内的历史推荐
            val calendar:Calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            calendar.set(Calendar.DAY_OF_MONTH,day - DATE_LINE)

            val time = calendar.time.time
            val _time = formatDate(it)!!.time

            _time > time
        }.forEach {
            Log.d(TAG,it)
            val _temp = it.split("-")
            val date = com.kcode.gankotlin.repository.Date(_temp[0],_temp[1],_temp[2])
            this.dates.add(date)
        }


    }

    fun formatDate(str:String):Date? {
        val FORMAT = "yyyy-MM-dd"
        val simpleFormat:SimpleDateFormat = SimpleDateFormat(FORMAT)
        return simpleFormat.parse(str)
    }
}