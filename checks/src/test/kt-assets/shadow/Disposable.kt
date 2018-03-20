package io.reactivex.disposables

class Disposable

fun Disposable.addTo(compositeDisposable: CompositeDisposable): Disposable = this