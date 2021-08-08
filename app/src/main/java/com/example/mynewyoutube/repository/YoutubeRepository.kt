package com.example.mynewyoutube.repository

import com.example.mynewyoutube.retrofit.ApiService
import kotlinx.coroutines.flow.flow

class YoutubeRepository(var apiService: ApiService) {

    suspend fun getYoutubeApiData() = flow { emit(apiService.getChannelVideos()) }
    suspend fun searchVideos( text:String) = flow { emit(apiService.searchVideo(q = text)) }
}