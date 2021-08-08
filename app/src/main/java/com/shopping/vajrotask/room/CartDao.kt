package com.shopping.vajrotask.room

import androidx.room.*


@Dao
interface CartDao {
    @Query("SELECT * FROM CartData ORDER BY id DESC")
    fun getAllData(): List<CartData>

    @Insert
    fun inserProduct(product: CartData)

    @Delete
    fun deleteProduct(product: CartData)

    @Update
    fun updateProduct(user: CartData)
}