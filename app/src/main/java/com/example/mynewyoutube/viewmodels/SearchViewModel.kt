package com.example.mynewyoutube.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewyoutube.models.YoutubeApiData
import com.example.mynewyoutube.repository.YoutubeRepository
import com.example.mynewyoutube.retrofit.ApiService
import com.example.mynewyoutube.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(var apiService: ApiService,text:String) : ViewModel() {
    private val youtubeLiveData = MutableLiveData<Resource<YoutubeApiData>>()
    private val youtubeRepository = YoutubeRepository(apiService)

    init {
        searchVideos(text)
    }

    private fun searchVideos(text:String) {
        viewModelScope.launch {
            youtubeLiveData.postValue(Resource.loading(null))
            youtubeRepository.searchVideos(text = text).catch { e ->
                youtubeLiveData.postValue(Resource.error(e.message.toString(), null))
            }.collect {
                youtubeLiveData.postValue(Resource.success(it))
            }

        }
    }

    fun getData(): MutableLiveData<Resource<YoutubeApiData>> {
        return youtubeLiveData
    }
}