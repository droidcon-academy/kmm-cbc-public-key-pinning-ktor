package com.example.publickeypinningkmmwithktor.httpclient

import io.ktor.client.HttpClient
import okhttp3.OkHttpClient

actual val correctPinningClient = HttpClient()

actual val incorrectPinningClient = HttpClient()

private fun getOkHttpClient(pins: Array<String>): OkHttpClient {
    TODO("Implementation is missing")
}
