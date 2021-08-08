package com.example.mynewyoutube.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewyoutube.models.YoutubeApiData
import kotlinx.coroutines.launch
import com.example.mynewyoutube.repository.YoutubeRepository
import com.example.mynewyoutube.retrofit.ApiService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import com.example.mynewyoutube.utils.Resource

class YoutubeViewModel(apiService: ApiService) : ViewModel() {

    private val youtubeLiveData = MutableLiveData<Resource<YoutubeApiData>>()
    private val youtubeRepository = YoutubeRepository(apiService)

    init {
        getVideos()
    }

    private fun getVideos() {
        viewModelScope.launch {
            youtubeLiveData.postValue(Resource.loading(null))
            youtubeRepository.getYoutubeApiData()
                .catch { e ->
                    youtubeLiveData.postValue(Resource.error(e.message ?: "Error", null))
                }.collect {
                    youtubeLiveData.postValue(Resource.success(it))
                }
        }
    }

    fun getYoutubeLiveData(): MutableLiveData<Resource<YoutubeApiData>> {
        return youtubeLiveData
    }
}