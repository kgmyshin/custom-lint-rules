package io.reactivex

import io.reactivex.disposables.Disposable

class Completable {

    companion object {
        fun just(target: Any): Completable = Completable()
    }

    fun subscribe(): Disposable = Disposable()

}