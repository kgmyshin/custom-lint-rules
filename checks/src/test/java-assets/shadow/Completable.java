package io.reactivex;

import io.reactivex.disposables.Disposable;

public class Completable {

    public static Completable just(Object target) {
        return new Completable();
    }

    public Disposable subscribe() {
        return new Disposable();
    }

}