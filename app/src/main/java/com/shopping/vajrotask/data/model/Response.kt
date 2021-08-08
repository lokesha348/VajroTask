package com.shopping.vajrotask.data.model

data class Response(
	val products: List<ProductsItem> = emptyList()
)

data class ProductsItem(
	val image: String? = null,
	val images: List<Any?>? = null,
	val quantity: Int? = null,
	val thumb: String? = null,
	val description: String? = null,
	val zoomThumb: String? = null,
	val special: String? = null,
	val price: String? = null,
	val productId: String? = null,
	val name: String? = null,
	val options: List<Any?>? = null,
	val id: String? = null,
	val href: String? = null,
	val sku: String? = null
)

