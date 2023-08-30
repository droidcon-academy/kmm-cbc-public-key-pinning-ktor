package com.example.publickeypinningkmmwithktor

internal const val PINNED_HOST_NAME = "publicobject.com"

internal val correctPins = arrayOf(
    "sha256/tK20XqQX9/O1Pye/fEIvMnLoaXxqMt+BAA1wHm7419A=",
    "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=",
    "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M="
)

internal val incorrectPins = arrayOf(
    "sha256/afwiKY3RxoMmLkuRW1l7QsPZTJPwDS2pdDROQjXw8ig=",
    "sha256/klO23nT2ehFDXCfx3eHTDRESMz3asj1muO+4aIdjiuY="
)