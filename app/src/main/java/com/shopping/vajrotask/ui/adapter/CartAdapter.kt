package com.shopping.vajrotask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.vajrotask.R
import com.shopping.vajrotask.databinding.CartItemBinding
import com.shopping.vajrotask.room.CartData

class CartAdapter(val listener: RowClickListener) :
    RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
    var items = ArrayList<CartData>()
    fun setListData(data: ArrayList<CartData>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(items[position])
        }
        holder.bind(items[position])

    }


    inner class MyViewHolder(val binding:CartItemBinding, val listener: RowClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CartData) {

            if (data!=null){
                binding.title.text=data.title
                Glide.with(binding.root.getContext()).load(data.image)
                    .into(binding.itemimageView);
                binding.productprice.text=data.price
                binding.quantity.text=data.qty
            }

          /*  deleteUserID.setOnClickListener {
                listener.onDeleteUserClickListener(data)
            }*/
        }
    }

    interface RowClickListener {
        fun onDeleteUserClickListener(item: CartData)
        fun onItemClickListener(item: CartData)
    }
}