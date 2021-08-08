package com.shopping.vajrotask.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.vajrotask.data.api.ApiClient
import com.shopping.vajrotask.data.api.ApiService
import com.shopping.vajrotask.data.model.ProductsItem
import com.shopping.vajrotask.data.model.Response
import com.shopping.vajrotask.databinding.ActivityMainBinding
import com.shopping.vajrotask.room.CartData
import com.shopping.vajrotask.ui.activityviewmodel.CartActivityViewModel
import com.shopping.vajrotask.ui.activityviewmodel.MainActivityViewModel
import com.shopping.vajrotask.ui.adapter.ProductItemAdapter
import com.shopping.vajrotask.utils.Status
import com.shopping.vajrotask.utils.ViewModelFactory

class MainActivity : AppCompatActivity(), ProductItemAdapter.RowClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductItemAdapter
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var cartviewModel: CartActivityViewModel
    var productList: List<ProductsItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupViewModel()
        setupUI()
        getRecyclerViewItem()

    }

    private fun retriveList(list: Response) {

        try {
            productList = list.products
            adapter = ProductItemAdapter(productList, this@MainActivity)
            binding!!.recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        } catch (e: Exception) {
        }

    }

    private fun getRecyclerViewItem() {
        viewModel.getProductItems().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        /* Toast.makeText(applicationContext, "its Called Maara", Toast.LENGTH_LONG)
                             .show()*/
                        resource.data?.let { list -> retriveList(list as Response) }
                    }
                    Status.ERROR -> {
                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
                        it.message?.let { it1 -> Log.v("ERRORLOADGETAPI", it1) }
                    }
                }
            }
        })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ApiClient.getRetrofit()?.let { ViewModelFactory(it.create(ApiService::class.java)) })
            .get(MainActivityViewModel::class.java)
        cartviewModel = ViewModelProviders.of(this).get(CartActivityViewModel::class.java)
    }

    private fun setupUI() {
        binding?.recyclerView?.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding?.recyclerView?.itemAnimator = DefaultItemAnimator()
        adapter = ProductItemAdapter(productList, this@MainActivity)
        binding!!.recyclerView.adapter = adapter
        binding.toolbar.cartimg.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
// To pass any data to next activity
//            intent.putExtra("keyIdentifier", value)
// start your next activity
            startActivity(intent)
        }
    }

    override fun onDeleteUserClickListener(productItem: CartData) {
        val title = productItem.title.toString()
        val imageData = productItem.image.toString()
        val price = productItem.price.toString()
        val qty = productItem.qty.toString()
        val values = CartData(0, title, imageData, price, qty)
        cartviewModel.deleteCart(values)
    }

    override fun onItemClickListener(productItem: CartData) {
        val title = productItem.title.toString()
        val imageData = productItem.image.toString()
        val price = productItem.price.toString()
        val qty = productItem.qty.toString()
        val values = CartData(0, title, imageData, price, qty)
        cartviewModel.inserCart(values)
    }
}