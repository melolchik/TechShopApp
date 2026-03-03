package ru.melolchik.techshopapp.network.api

import retrofit2.http.GET
import ru.melolchik.techshopapp.network.dto.ProductDto

interface ProductApi {

    @GET("/")
    suspend fun getProducts(): List<ProductDto>
}