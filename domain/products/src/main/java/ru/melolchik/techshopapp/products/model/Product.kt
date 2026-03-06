package ru.melolchik.techshopapp.products.model

//@Parcelize
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
){

    companion object{
        val Test = Product(
            id = "1",
            model = "Model",
            description = "Description",
            features = "Features",
            categories = "Categories",
            price = 100.0,
            imageUrl = "https://picsum.photos/200/300",
            editedAt = System.currentTimeMillis()
        )

    }

}

