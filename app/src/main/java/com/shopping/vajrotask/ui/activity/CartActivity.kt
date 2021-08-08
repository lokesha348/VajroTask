package com.shopping.vajrotask.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopping.vajrotask.R
import com.shopping.vajrotask.databinding.ActivityCartBinding
import com.shopping.vajrotask.room.CartData
import com.shopping.vajrotask.ui.activityviewmodel.CartActivityViewModel
import com.shopping.vajrotask.ui.adapter.CartAdapter

class CartActivity : AppCompatActivity(), CartAdapter.RowClickListener {
    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter
    lateinit var viewModel: CartActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        val view = binding.root
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            cartAdapter = CartAdapter(this@CartActivity)
            adapter = cartAdapter
        }
        setContentView(view)
        viewModel = ViewModelProviders.of(this).get(CartActivityViewModel::class.java)
        viewModel.getAllCartAbserver().observe(this, Observer {
            cartAdapter.setListData(ArrayList(it))
            cartAdapter.notifyDataSetChanged()
        })
        /* setupViewModel()
         setupUI()
         getRecyclerViewItem()*/
    }

    override fun onDeleteUserClickListener(item: CartData) {

    }

    override fun onItemClickListener(item: CartData) {
    }
}