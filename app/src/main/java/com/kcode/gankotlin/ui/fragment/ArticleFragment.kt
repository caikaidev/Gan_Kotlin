package com.kcode.gankotlin.ui.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kcode.gankotlin.R
import com.kcode.gankotlin.repository.Article
import com.kcode.gankotlin.ui.activity.ArticleDetailActivity
import com.kcode.gankotlin.ui.adapter.ArticleAdapter
import com.kcode.gankotlin.utils.toast
import kotlinx.android.synthetic.main.fragment_article_list.*

/**
 * Created by caik on 2017/5/31.
 */
open abstract class ArticleFragment : BaseFragment() {

    var adapter: ArticleAdapter? = null


    override fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = ArticleAdapter(activity!!.applicationContext, R.layout.item_article)
        recyclerView.adapter = adapter

        adapter!!.setOnLoadMoreListener({ loadMore() }, recyclerView)
        adapter!!.onItemClickListener = BaseQuickAdapter.OnItemClickListener {
            adapter, view, position ->
            start2Detail(adapter.data[position] as Article)
        }

    }

    private fun loadMore() {
        pageNumber++
        isRefresh = false
        loadData(pageSize, pageNumber)
    }

    override fun loadError() {
        if (activity != null) {
            activity!!.toast(R.string.load_failed)
        }
    }

    override fun loadSuccess(data: List<Article>) {
        setUp(data)
    }

    private fun setUp(data: List<Article>) {
        Log.d(TAG, data.toString())

        if (isRefresh) {
            adapter!!.setNewData(data)
        } else {
            adapter!!.addData(data)
        }
    }

    override fun loadFinish() {
        resetStatus()
    }

    private fun resetStatus() {
        if (swipeLayout!!.isRefreshing) {
            swipeLayout!!.isRefreshing = false
        }

        adapter!!.loadMoreComplete()
    }


    fun start2Detail(article: Article) {
        val intent = Intent(activity, ArticleDetailActivity::class.java)
        intent.putExtra("desc", article.desc)
        intent.putExtra("url", article.url)
        startActivity(intent)
    }

}