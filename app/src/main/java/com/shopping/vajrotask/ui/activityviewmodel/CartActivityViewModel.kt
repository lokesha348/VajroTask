package com.shopping.vajrotask.ui.activityviewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.shopping.vajrotask.room.CartData
import com.shopping.vajrotask.room.RoomAppDB

class CartActivityViewModel(app: Application) : AndroidViewModel(app) {
    lateinit var allCartdata: MutableLiveData<List<CartData>>

    init {
        allCartdata = MutableLiveData()
    }

    fun getAllData() {
        val cartDao = RoomAppDB.getRoomAppDB((getApplication()))?.cartDao()
        val list = cartDao?.getAllData()

        allCartdata.postValue(list)
    }

    fun getAllCartAbserver(): MutableLiveData<List<CartData>> {
        return allCartdata
    }

    fun inserCart(entity: CartData) {
        try {
            val cartDao = RoomAppDB.getRoomAppDB(getApplication())?.cartDao()
            cartDao?.inserProduct(entity)
            Toast.makeText(getApplication(),"Inserted",Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(getApplication(),""+e,Toast.LENGTH_SHORT).show()
        }
//        getAllData()
    }

    fun deleteCart(entity: CartData) {
        val cartDao = RoomAppDB.getRoomAppDB(getApplication())?.cartDao()
        cartDao?.deleteProduct(entity)
        getAllData()
    }

    fun updateCart(entity: CartData) {
        val cartDao = RoomAppDB.getRoomAppDB(Application())?.cartDao()
        cartDao?.updateProduct(entity)
        getAllData()
    }
}