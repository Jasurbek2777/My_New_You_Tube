package com.example.mynewyoutube.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
        val BASE_URL = "https://www.googleapis.com/youtube/v3/"
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiService = getRetrofit().create(ApiService::class.java)

}