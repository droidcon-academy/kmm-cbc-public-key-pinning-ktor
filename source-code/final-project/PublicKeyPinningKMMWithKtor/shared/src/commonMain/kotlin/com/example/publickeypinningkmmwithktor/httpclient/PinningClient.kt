package com.example.publickeypinningkmmwithktor.httpclient

import io.ktor.client.HttpClient

expect val correctPinningClient: HttpClient
expect val incorrectPinningClient: HttpClient