package ru.melolchik.techshopapp.splash.data.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ru.melolchik.techshopapp.products.usecase.SyncProductsUseCase

@HiltWorker
class ProductSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val syncProductsUseCase: SyncProductsUseCase
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            syncProductsUseCase()
            Result.success()
        } catch (e: Exception) {
            Log.d("ProductSyncWorker","ex = $e")
            Result.retry()
        }
    }
}