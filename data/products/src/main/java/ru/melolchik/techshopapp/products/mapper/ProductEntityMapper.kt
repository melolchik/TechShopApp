package ru.melolchik.techshopapp.products.mapper

import ru.melolchik.techshopapp.database.entity.ProductEntity
import ru.melolchik.techshopapp.products.model.Product

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        model = model,
        description = description,
        features = features,
        categories = categories,
        price = price,
        imageUrl = imageUrl,
        editedAt = editedAt,
        isFavorite = isFavorite
    )
}