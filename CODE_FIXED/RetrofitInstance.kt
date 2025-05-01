package com.example.userprofiles

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: QuoteApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://zenquotes.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApiService::class.java)
    }
}