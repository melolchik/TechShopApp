package ru.melolchik.techshopapp.products.model

data class Product(
    val id: String,
    val model: String,
    val description: String,
    val features: String,
    val categories: String,
    val price: Double,
    val imageUrl: String,
    val editedAt: Long,
    val isFavorite: Boolean = false
)
