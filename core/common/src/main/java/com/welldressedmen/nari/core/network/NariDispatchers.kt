package com.welldressedmen.nari.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val nariDispatcher: NariDispatchers)

enum class NariDispatchers {
    Default,
    IO,
}
