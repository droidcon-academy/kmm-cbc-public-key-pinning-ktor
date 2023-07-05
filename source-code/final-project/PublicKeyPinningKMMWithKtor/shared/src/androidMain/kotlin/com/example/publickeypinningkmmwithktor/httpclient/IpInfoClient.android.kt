package com.example.publickeypinningkmmwithktor.httpclient

import com.example.publickeypinningkmmwithktor.PINNED_HOST_NAME
import com.example.publickeypinningkmmwithktor.correctPins
import com.example.publickeypinningkmmwithktor.incorrectPins
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient

actual val correctPinningClient = HttpClient(OkHttp) {
    engine {
        preconfigured = getOkHttpClient(correctPins)
    }
}

actual val incorrectPinningClient = HttpClient(OkHttp) {
    engine {
        preconfigured = getOkHttpClient(incorrectPins)
    }
}

private fun getOkHttpClient(pins: Array<String>): OkHttpClient {
    val certificatePinner =
        CertificatePinner.Builder().add(pattern = PINNED_HOST_NAME, pins = pins).build()
    return OkHttpClient.Builder().certificatePinner(certificatePinner).build()
}
