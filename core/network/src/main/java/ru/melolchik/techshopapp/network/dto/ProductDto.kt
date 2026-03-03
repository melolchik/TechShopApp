package ru.melolchik.techshopapp.network.dto

import com.google.gson.annotations.SerializedName

/*
 {
    "id": "1",
    "model": "Мотоблок Patriot Победа 440",
    "description": "Бензиновый мотоблок для вспашки и культивации",
    "features": "Двигатель: 7 л.с.\nШирина обработки: 85 см\nГлубина вспашки: 30 см\nВес: 78 кг\nКоробка передач: 2 вперед / 1 назад\nОбъем бака: 3.6 л",
    "categories": [
      "Бензиновые",
      "Самоходные"
    ],
    "imageUrl": "https://avatars.mds.yandex.net/get-mpic/7052428/img_id4715440000706980715.jpeg/orig",
    "price": 58990,
    "editedAt": 1712345678
  },
 */
data class ProductDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("features")
    val features: String,
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("price")
    val price: Double,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("editedAt")
    val editedAt: Long
)
