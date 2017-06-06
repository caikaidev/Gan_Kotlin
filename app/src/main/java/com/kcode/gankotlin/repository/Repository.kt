package com.kcode.gankotlin.repository

/**
 * Created by caik on 2017/6/2.
 */
data class History(val date:String,val content:String)

data class PublishedDate(val error:Boolean,val results:List<String>)