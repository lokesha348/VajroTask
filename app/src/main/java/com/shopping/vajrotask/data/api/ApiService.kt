package com.shopping.vajrotask.data.api

import com.shopping.vajrotask.data.model.Response
import com.shopping.vajrotask.utils.Constants
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.getProducts)
    suspend fun getProducts(): Response
}