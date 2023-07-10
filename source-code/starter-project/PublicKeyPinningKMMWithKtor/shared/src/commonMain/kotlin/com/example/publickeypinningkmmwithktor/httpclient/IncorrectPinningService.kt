package com.example.publickeypinningkmmwithktor.httpclient

import io.ktor.client.call.body
import io.ktor.client.request.get

class IncorrectPinningService : PinningService {
    @Throws(Throwable::class)
    override suspend fun getData(): String {
        throw IllegalStateException("Implementation is missing")
    }
}