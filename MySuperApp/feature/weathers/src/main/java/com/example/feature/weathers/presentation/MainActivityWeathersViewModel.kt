package com.example.feature.weathers.presentation

import androidx.lifecycle.MutableLiveData
import com.example.common.BaseActivityViewModel
import com.example.feature.weathers.data.response.WeathersResponse
import com.example.feature.weathers.data.repo.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityWeathersViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseActivityViewModel() {

    var mainLiveDataWeathers = MutableLiveData<WeathersResponse>()
    var dataLang : String? = "tr"
    var job : Job? = null

    var exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    }

    fun getWeathers(dataCity: String?) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = remoteDataSource.getWeathers(dataCity, dataLang)
            withContext(Dispatchers.Main + exceptionHandler) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        mainLiveDataWeathers.value = it
                    }
                }
            }
        }
    }
}