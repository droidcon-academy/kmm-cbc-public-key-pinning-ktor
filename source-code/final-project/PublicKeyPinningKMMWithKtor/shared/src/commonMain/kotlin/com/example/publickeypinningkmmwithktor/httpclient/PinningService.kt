package com.example.publickeypinningkmmwithktor.httpclient

interface PinningService {

    @Throws(Throwable::class)
    suspend fun getData(): String
}