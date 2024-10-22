package org.example.thirdwheel

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform