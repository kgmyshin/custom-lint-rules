package io.reactivex

import io.reactivex.disposables.Disposable

class Observable {

    companion object {
        fun just(target: Any): Observable = Observable()
    }

    fun subscribe(): Disposable = Disposable()

}