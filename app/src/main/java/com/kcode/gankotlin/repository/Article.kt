package com.kcode.gankotlin.repository

/**
 * Created by caik on 2017/6/1.
 */
data class Article(val _id:String,val createdAt:String,val desc:String,
                   var images:Array<String>,val publishedAt:String,val source:String,val type:String,val url:String,
                   val used:Boolean,val who:String)
