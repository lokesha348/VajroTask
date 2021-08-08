package com.shopping.vajrotask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shopping.vajrotask.R
import com.shopping.vajrotask.data.model.ProductsItem
import com.shopping.vajrotask.databinding.ProductItemBinding
import com.shopping.vajrotask.room.CartData

class ProductItemAdapter(
    private val productList: List<ProductsItem>,
    val listener: RowClickListener
) :
    RecyclerView.Adapter<ProductItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(productList[position])
        val context = holder.binding.relative.context

        holder.binding.increase.setOnClickListener {
            var qun: Int
            qun = holder.binding.quantity.text.toString().toInt()
            if (qun >= 0) {
                qun++
                holder.binding.quantity.text = qun.toString()
                val productID = productList.get(position).productId
                val title = productList.get(position).name
                val imageData = productList.get(position).image
                val price = productList.get(position).price.toString()
                val qty = qun
                val values = CartData(
                    0, title.toString(), imageData.toString(), price,
                    qun.toString()
                )
                holder.listener.onItemClickListener(values)
            }
        }
        holder.binding.decrease.setOnClickListener {
            var qun: Int
            qun = holder.binding.quantity.text.toString().toInt()
            if (qun > 0) {
                qun--
                holder.binding.quantity.text = qun.toString()
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount() = productList.size

    inner class ViewHolder(val binding: ProductItemBinding, val listener: RowClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(productItem: ProductsItem) {
            if (productItem != null) {
                binding.producctname.text = productItem.name
                binding.productprice.text = productItem.price
                Glide.with(binding.root.getContext()).load(productItem.image)
                    .into(binding.itemimageView);
//                binding.itemimageView

            }
        }
    }

    interface RowClickListener {
        fun onDeleteUserClickListener(productItem: CartData)
        fun onItemClickListener(productItem: CartData)
    }

    fun setImageUrl(imgView: ImageView, imgUrl: String?) {

        imgUrl?.let {
            Glide.with(imgView.context)
                .load(imgUrl)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                )
                .into(imgView)
        }
    }
}