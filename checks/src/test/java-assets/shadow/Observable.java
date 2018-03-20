package io.reactivex;

import io.reactivex.disposables.Disposable;

public class Observable {

    public static Observable just(Object target) {
        return new Observable();
    }

    public Disposable subscribe() {
        return new Disposable();
    }

}