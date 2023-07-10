package com.example.publickeypinningkmmwithktor.httpclient

import io.ktor.client.call.body
import io.ktor.client.request.get

class CorrectPinningService : PinningService {
    @Throws(Throwable::class)
    override suspend fun getData(): String {
        return correctPinningClient.get("https://publicobject.com/").body()
    }
}