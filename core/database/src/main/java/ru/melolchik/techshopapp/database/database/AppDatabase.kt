package ru.melolchik.techshopapp.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.melolchik.techshopapp.database.dao.ProductDao
import ru.melolchik.techshopapp.database.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}