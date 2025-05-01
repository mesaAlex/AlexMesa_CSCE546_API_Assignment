package com.example.userprofiles

import retrofit2.http.GET

interface QuoteApiService {
    @GET("random")
    suspend fun getRandomQuote(): List<Quote>
}