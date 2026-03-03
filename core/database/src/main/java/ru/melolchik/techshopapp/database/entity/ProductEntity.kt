package ru.melolchik.techshopapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
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
