package test.pkg

import io.reactivex.Single

class MainKt {

    fun aaa() {
        Single.just("aa").subscribe()
    }

}