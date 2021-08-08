package com.shopping.vajrotask.ui.activityviewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shopping.vajrotask.data.api.ApiService
import com.shopping.vajrotask.room.CartData
import com.shopping.vajrotask.room.RoomAppDB
import com.shopping.vajrotask.utils.Resource
import kotlinx.coroutines.Dispatchers


class MainActivityViewModel(private val apiService: ApiService) : ViewModel() {
    lateinit var allCartdata: MutableLiveData<List<CartData>>
    init {
        allCartdata= MutableLiveData()
    }
    fun getProductItems() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getProducts()))
        } catch (e: Exception) {
            Resource.error(
                data = null,
                msg = e.message ?: "Error Occurred on getLoads Api"
            )
        }
    }

}