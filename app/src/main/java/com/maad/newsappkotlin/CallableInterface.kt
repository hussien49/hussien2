package com.maad.newsappkotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CallableInterface {

    @GET("api/quotes")
    fun getData ()/*(@Query("category") cat: String?, @Query("country") code: String?)*/: Call<NewsModel>

}