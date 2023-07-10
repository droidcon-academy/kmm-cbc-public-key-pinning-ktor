package com.example.publickeypinningkmmwithktor.httpclient

import com.example.publickeypinningkmmwithktor.PINNED_HOST_NAME
import com.example.publickeypinningkmmwithktor.correctPins
import com.example.publickeypinningkmmwithktor.incorrectPins
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.engine.darwin.certificates.CertificatePinner
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

actual val correctPinningClient = HttpClient(Darwin) {
    engine {
        handleChallenge(getCertificatePinner(correctPins))
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
    }
}

actual val incorrectPinningClient = HttpClient(Darwin) {
    engine {
        handleChallenge(getCertificatePinner(incorrectPins))
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
    }
}

private fun getCertificatePinner(pins: Array<String>): CertificatePinner {
    return CertificatePinner.Builder().add(pattern = PINNED_HOST_NAME, pins = pins).build()
}