package ru.melolchik.techshopapp.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.melolchik.techshopapp.splash.data.worker.ProductSyncWorker
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val workManager: WorkManager
) : ViewModel() {

    private val _isFinished = MutableStateFlow(false)
    val isFinished: StateFlow<Boolean> = _isFinished

    fun startSync() {

        val request = OneTimeWorkRequestBuilder<ProductSyncWorker>()
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        workManager.enqueue(request)

        workManager.getWorkInfoByIdLiveData(request.id)
            .observeForever { info ->
                if (info.state.isFinished) {
                    _isFinished.value = true
                }
            }
    }
}