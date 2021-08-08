package com.example.mynewyoutube.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynewyoutube.retrofit.ApiService
import java.lang.IllegalArgumentException

class SearchViewModelFactory(var apiService: ApiService,var text:String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(apiService,text) as T
        }
        throw IllegalArgumentException("Error Search view model")
    }
}