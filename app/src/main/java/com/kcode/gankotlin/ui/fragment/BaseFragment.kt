package com.kcode.gankotlin.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kcode.gankotlin.R
import com.kcode.gankotlin.net.Api
import com.kcode.gankotlin.repository.Article
import com.kcode.gankotlin.repository.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_article_list.*

/**
 * Created by caik on 2017/6/1.
 */
abstract class BaseFragment : Fragment() {

    val TAG: String = BaseFragment::class.java.simpleName

    val pageSize = 10
    var pageNumber = 1
    var isRefresh = false

    var activity:Activity? = null
    var rootView:View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater!!.inflate(R.layout.fragment_base, container, false)
        }
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        swipeLayout.setOnRefreshListener({
            pageNumber = 1
            isRefresh = true
            loadData(pageSize,pageNumber)
        })


        loadData(pageSize,pageNumber)

    }

    protected fun loadData(pageSize: Int, pageNumber: Int) {
        val api = Api.Factory.create()
        api.getData(getType(), pageSize, pageNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    parseResult(result)
                }, {
                    _ ->
                        loadError()
                        loadFinish()
                })
    }

    fun parseResult(result: Result) {
        Log.d(TAG, result.toString())

        if (result.error) {
            loadError()
        }else{
            loadSuccess(result.results)
        }
        loadFinish()

    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        this.activity = activity
    }

    override fun onDetach() {
        super.onDetach()
        this.activity = null
    }

    abstract fun initRecyclerView()

    abstract fun getType(): String

    abstract fun loadError()

    abstract fun loadSuccess(data: List<Article>)

    abstract fun loadFinish()
}