package test.pkg

import io.reactivex.Single

class Main {

    fun main() {
        Single.just("test").subscribe()
    }

}