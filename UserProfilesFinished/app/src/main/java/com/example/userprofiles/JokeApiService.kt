package com.example.userprofiles

import retrofit2.http.GET

interface JokeApiService {
    @GET("random_joke")
    suspend fun getRandomJoke(): Joke
}