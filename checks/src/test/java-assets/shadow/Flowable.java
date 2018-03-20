package io.reactivex;

import io.reactivex.disposables.Disposable;

public class Flowable {

    public static Flowable just(Object target) {
        return new Flowable();
    }

    public Disposable subscribe() {
        return new Disposable();
    }

}