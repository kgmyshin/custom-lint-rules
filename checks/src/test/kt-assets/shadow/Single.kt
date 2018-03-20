package io.reactivex

import io.reactivex.disposables.Disposable

class Single {

    companion object {
        fun just(target: Any): Single = Single()
    }

    fun subscribe(): Disposable = Disposable()

}