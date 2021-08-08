package com.shopping.vajrotask.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shopping.vajrotask.data.api.ApiService
import com.shopping.vajrotask.ui.activityviewmodel.MainActivityViewModel

class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
           return MainActivityViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}