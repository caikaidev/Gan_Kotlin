package com.kcode.gankotlin.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.SimpleItemAnimator
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kcode.gankotlin.R
import com.kcode.gankotlin.common.Type
import com.kcode.gankotlin.repository.Article
import com.kcode.gankotlin.ui.activity.PhotoActivity
import com.kcode.gankotlin.utils.toast
import kotlinx.android.synthetic.main.fragment_base.*

/**
 * Created by caik on 2017/5/31.
 */
class WelfareFragment : BaseFragment() {

    var adapter: GirlAdapter? = null

    companion object {
        fun newInstance(): WelfareFragment {
            return WelfareFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.visibility = View.VISIBLE
    }

    override fun initRecyclerView() {
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        var simpleAnimator: SimpleItemAnimator = recyclerView.itemAnimator as SimpleItemAnimator
        simpleAnimator.supportsChangeAnimations = false

        adapter = GirlAdapter(activity!!.applicationContext, R.layout.item_girl)
        adapter!!.onItemClickListener = BaseQuickAdapter.OnItemClickListener {
            adapter, view, position -> start2PhotoActivity(adapter.getItem(position) as Article)
        }

        recyclerView.adapter = adapter

        adapter!!.setOnLoadMoreListener({
            pageNumber++
            isRefresh = false
            loadData(pageSize, pageNumber)
        }, recyclerView)
    }

    private fun start2PhotoActivity(article: Article) {
        val intent = Intent(activity, PhotoActivity::class.java)
        intent.putExtra("url", article.url)
        activity!!.startActivity(intent)
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
        if (isRefresh) {
            adapter!!.setNewData(data)
        } else {
            adapter!!.addData(data)
        }
    }

    override fun loadFinish() {
        if (swipeLayout!!.isRefreshing) {
            swipeLayout!!.isRefreshing = false
        }

        adapter!!.loadMoreComplete()
    }

    override fun getType(): String {
        return Type.福利.name
    }

    class GirlAdapter(var context: Context, layoutId: Int) : BaseQuickAdapter<Article, BaseViewHolder>(layoutId) {

        override fun convert(viewHolder: BaseViewHolder?, article: Article?) {
            val imageView = viewHolder!!.getView<ImageView>(R.id.image)
            Glide.with(context).load(article!!.url).into(imageView)
        }

    }

}