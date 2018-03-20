package io.reactivex;

import io.reactivex.disposables.Disposable;

public class Maybe {

    public static Maybe just(Object target) {
        return new Maybe();
    }

    public Disposable subscribe() {
        return new Disposable();
    }

}