package com.example.publickeypinningkmmwithktor

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform