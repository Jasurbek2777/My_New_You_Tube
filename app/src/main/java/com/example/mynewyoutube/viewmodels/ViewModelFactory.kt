package com.example.mynewyoutube.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynewyoutube.retrofit.ApiService
import java.lang.IllegalArgumentException

class ViewModelFactory(var apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(YoutubeViewModel::class.java)) {
            return YoutubeViewModel(apiService) as T
        }
        throw IllegalArgumentException("Error view model")
    }
}