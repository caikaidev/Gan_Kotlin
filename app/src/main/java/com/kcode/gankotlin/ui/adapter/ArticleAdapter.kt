package com.kcode.gankotlin.ui.adapter

import android.content.Context
import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kcode.gankotlin.R
import com.kcode.gankotlin.repository.Article
import java.text.SimpleDateFormat

/**
 * Created by caik on 2017/6/1.
 */
class ArticleAdapter(var context: Context,layoutId:Int) : BaseQuickAdapter<Article, BaseViewHolder>(layoutId) {

    val sdf:SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    override fun convert(viewHolder: BaseViewHolder?, article: Article?) {
        viewHolder!!.setText(R.id.title,article!!.desc)
        viewHolder.setText(R.id.who,article!!.who)
        viewHolder.setText(R.id.type,article!!.type)
        viewHolder.setText(R.id.publishedAt,DateUtils.getRelativeTimeSpanString(sdf.parse(article!!.publishedAt).time))

        val image: ImageView = viewHolder.getView(R.id.image)

        if (article!!.images == null || article!!.images.size == 0) {
            image.visibility = View.GONE
        }else{
            image.visibility = View.VISIBLE
            val width:Int = context.resources.getDimension(R.dimen.article_image_width).toInt()
            Glide.with(context).load("${article!!.images[0]}?imageView2/0/w/$width").into(image)
        }

    }
}