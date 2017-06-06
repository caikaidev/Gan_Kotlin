package com.kcode.gankotlin.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kcode.gankotlin.R
import com.kcode.gankotlin.net.Api
import com.kcode.gankotlin.repository.History
import com.kcode.gankotlin.ui.activity.HistoryDetailActivity
import com.kcode.gankotlin.ui.activity.MainActivity
import com.kcode.gankotlin.utils.dismissProgress
import com.kcode.gankotlin.utils.showProgress
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_history.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/**
 * Created by caik on 2017/6/2.
 */
class HistoryFragment : Fragment() {

    var activity: MainActivity? = null

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        loadPublishedDate()
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        this.activity = activity as MainActivity?

    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    fun loadPublishedDate() {
        showProgress()
        val api = Api.Factory.create()
        api.getHistory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    setUpRecyclerView(parseHtml(result.string()))
                }, {}, { dismissProgress() })
    }

    private fun parseHtml(html: String): List<History> {
        val doc: Document = Jsoup.parse(html)
        val typo: Elements = doc.getElementsByClass("typo")
        var data: MutableList<History> = arrayListOf()

        var history: History

        typo.select("a").listIterator().forEach {
            history = History(it.attr("href").substring(1), it.text())
            data.add(history)
        }
        return data
    }

    fun setUpRecyclerView(data: List<History>) {
        val adapter: HistoryAdapter = HistoryAdapter(R.layout.item_history, data)
        adapter.setOnItemClickListener({ adapter, view, position ->
            val history: History = adapter.getItem(position) as History
            start2HistoryDetail(history.date)
        })
        recyclerView.adapter = adapter
    }

    private fun start2HistoryDetail(date: String) {
        val intent: Intent = Intent(activity, HistoryDetailActivity::class.java)
        intent.putExtra("date", date)
        activity!!.startActivity(intent)
    }

    class HistoryAdapter(layoutId: Int, data: List<History>) : BaseQuickAdapter<History, BaseViewHolder>(layoutId, data) {
        override fun convert(p0: BaseViewHolder?, p1: History?) {
            p0!!.setText(R.id.content, "${p1!!.content}(${p1!!.date})")
        }
    }


    private fun showProgress() {
        if (activity != null) {
            activity!!.showProgress()
        }
    }

    private fun dismissProgress() {
        if (activity != null) {
            activity!!.dismissProgress()
        }
    }

}