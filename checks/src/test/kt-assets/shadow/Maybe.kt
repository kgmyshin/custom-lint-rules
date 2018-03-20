package io.reactivex

import io.reactivex.disposables.Disposable

class Maybe {

    companion object {
        fun just(target: Any): Maybe = Maybe()
    }

    fun subscribe(): Disposable = Disposable()

}