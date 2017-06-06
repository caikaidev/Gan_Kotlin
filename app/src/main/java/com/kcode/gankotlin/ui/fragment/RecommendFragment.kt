package com.kcode.gankotlin.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kcode.gankotlin.R
import com.kcode.gankotlin.common.Type
import com.kcode.gankotlin.net.Api
import com.kcode.gankotlin.repository.Article
import com.kcode.gankotlin.ui.activity.ArticleDetailActivity
import com.kcode.gankotlin.ui.adapter.RecommendAdapter
import com.kcode.gankotlin.utils.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_recommend.*
import org.json.JSONObject

/**
 * Created by caik on 2017/6/5.
 */
class RecommendFragment : Fragment() {

    companion object {

        fun newInstance(date: String): RecommendFragment {
            val fragment: RecommendFragment = RecommendFragment()
            val args: Bundle = Bundle()
            if (!date.isEmpty()) {
                args.putString("date", date)
            }
            fragment.arguments = args
            return fragment
        }
    }

    var date: String? = null
    var imageUrl: String? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        date = arguments.getString("date")
        return inflater!!.inflate(R.layout.fragment_recommend, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDate()
    }

    fun loadDate() {
        val api = Api.Factory.create()
        api.getDataByDate(date!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    result ->
                    setUpView(parseResult(result.string())!!)
                }

    }

    fun parseResult(result: String): List<Article>? {
        val jsonObject = JSONObject(result)
        val error = jsonObject.getBoolean("error")
        if (error) {
            activity.toast(R.string.load_failed)
            return null
        }

        val results = jsonObject.getJSONObject("results")
        val data: MutableList<Article> = arrayListOf()

        val gson = Gson()
        val type = object : TypeToken<List<Article>>() {}.type
        results.keys().forEach {
            if (it != Type.福利.name) {
                data.addAll(gson.fromJson<List<Article>>(results.getJSONArray(it).toString(), type))
            } else {
                val array = results.getJSONArray(it)
                if (array.length() >= 0) {
                    imageUrl = array.getJSONObject(0).getString("url")
                }
            }
        }

        data.forEach {

        }

        return data
    }

    fun setUpView(data: List<Article>){
        Glide.with(activity)
                .load(imageUrl!!)
                .into(welFare)

        val adapter = RecommendAdapter(R.layout.item_recommend, data)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = adapter

        adapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener {
            adapter, view, position -> start2Detail(adapter.getItem(position) as Article)
        }

    }

    fun start2Detail(article: Article) {
        val intent = Intent(activity, ArticleDetailActivity::class.java)
        intent.putExtra("desc", article.desc)
        intent.putExtra("url", article.url)
        startActivity(intent)
    }

}