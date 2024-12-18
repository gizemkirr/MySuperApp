package com.example.common

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase<response : BaseResponse, Params>() {

    abstract fun useCaseFollower(params : Params?) : Single<response>

    private val compositeDisposable = CompositeDisposable()

    fun add(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    fun clear(){
        compositeDisposable.clear()
    }

    fun execute(observer: DisposableSingleObserver<response>, params: Params?){
        add(useCaseFollower(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer))
    }

}