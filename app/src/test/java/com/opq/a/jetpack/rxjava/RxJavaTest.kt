package com.opq.a.jetpack.rxjava

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.internal.operators.observable.ObservableDoOnEach
import io.reactivex.internal.operators.observable.ObservableFromArray
import io.reactivex.internal.operators.observable.ObservableSubscribeOn
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class RxJavaTest {
    @Test
    fun testRxJava() {
        println(Thread.currentThread().id)
        // RxJavaPlugins.setErrorHandler(Functions.ON_ERROR_MISSING)
        val observableFromArray = Observable.just(1, 2, 3, 4, 5) as ObservableFromArray
        val observableSubscribeOn = observableFromArray.subscribeOn(Schedulers.io()) as ObservableSubscribeOn
        val observableDoOnEach = observableSubscribeOn.doOnNext {
            println("it:$it")
        } as ObservableDoOnEach
        observableDoOnEach.subscribe(object : Observer<Int> {
            override fun onNext(t: Int) {}
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {}
        })

        Thread.sleep(10 * 1000)
    }

}