package com.example.common

import io.reactivex.observers.DisposableSingleObserver

abstract class BaseObserver<response: BaseResponse> : DisposableSingleObserver<response>() {

    abstract fun responseSuccess(response: response)

    abstract fun responseError(e : Throwable)

    override fun onSuccess(t: response) {
        responseSuccess(t)
    }

    override fun onError(e: Throwable) {
        responseError(e)
    }
}