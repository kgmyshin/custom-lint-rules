package io.reactivex;

import io.reactivex.disposables.Disposable;

public class Single {

    public static Single just(Object target) {
        return new Single();
    }

    public Disposable subscribe() {
        return new Disposable();
    }

}