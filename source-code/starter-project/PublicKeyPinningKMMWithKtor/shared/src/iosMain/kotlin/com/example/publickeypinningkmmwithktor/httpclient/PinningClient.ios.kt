package com.example.publickeypinningkmmwithktor.httpclient

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.certificates.CertificatePinner

actual val correctPinningClient = HttpClient()

actual val incorrectPinningClient = HttpClient()

private fun getCertificatePinner(pins: Array<String>): CertificatePinner {
    TODO("Implementation is missing")
}