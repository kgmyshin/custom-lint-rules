package test.pkg

import io.reactivex.Single

class MainKt {

    fun ng() {
        Single.just("aa").subscribe()
    }

}