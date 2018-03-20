package io.reactivex

import io.reactivex.disposables.Disposable

class Flowable {

    companion object {
        fun just(target: Any): Flowable = Flowable()
    }

    fun subscribe(): Disposable = Disposable()

}