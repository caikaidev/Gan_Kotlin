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

  private lateinit var articleAdapter: ArticleAdapter

  override fun initRecyclerView() {

    articleAdapter = ArticleAdapter(context!!, R.layout.item_article)
    articleAdapter.setOnLoadMoreListener({ loadMore() }, recyclerView)
    articleAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
      start2Detail(adapter.data[position] as Article)
    }

    with(recyclerView) {
      layoutManager = LinearLayoutManager(context)
      adapter = articleAdapter
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
      articleAdapter.setNewData(data)
    } else {
      articleAdapter.addData(data)
    }
  }

  override fun loadFinish() {
    resetStatus()
  }

  private fun resetStatus() {
    if (swipeLayout.isRefreshing) {
      swipeLayout.isRefreshing = false
    }

    articleAdapter.loadMoreComplete()
  }


  private fun start2Detail(article: Article) {
    val intent = Intent(activity, ArticleDetailActivity::class.java).apply {
      putExtra("desc", article.desc)
      putExtra("url", article.url)
    }

    startActivity(intent)
  }

}