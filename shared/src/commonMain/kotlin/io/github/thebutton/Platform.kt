package io.github.thebutton

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform