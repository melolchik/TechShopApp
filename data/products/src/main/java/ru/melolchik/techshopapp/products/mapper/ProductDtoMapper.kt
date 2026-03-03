package ru.melolchik.techshopapp.products.mapper

import ru.melolchik.techshopapp.database.entity.ProductEntity
import ru.melolchik.techshopapp.network.dto.ProductDto


fun ProductDto.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        model = model,
        description = description,
        features = features,
        categories = categories.joinToString(","),
        price = price,
        imageUrl = imageUrl,
        editedAt = editedAt
    )
}