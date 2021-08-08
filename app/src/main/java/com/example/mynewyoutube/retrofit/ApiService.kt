package com.example.mynewyoutube.retrofit

import com.example.mynewyoutube.models.Item
import com.example.mynewyoutube.models.YoutubeApiData
import com.example.mynewyoutube.utils.DeveloperKey
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun getChannelVideos(
        @Query("key") key: String = "AIzaSyCoBf0_MoDqpru0LLMNWtiWyJTqqc5gsow",
        @Query("channelId") channelId: String = "UCOH0xOR3ZUiqFVd6cf2djiQ",
        @Query("part") part: String = "snippet,id",
        @Query("order") order: String = "date",
        @Query("maxResults") maxResults: Int = 35
    ): YoutubeApiData


    @GET("search")
    suspend fun searchVideo(
        @Query("key") key: String = DeveloperKey.KEY,
        @Query("part") part: String = "snippet",
        @Query("q") q: String = "android",
        @Query("type") type: String = "video",
        @Query("maxResults") maxResults: Int = 50,
    ): YoutubeApiData
}