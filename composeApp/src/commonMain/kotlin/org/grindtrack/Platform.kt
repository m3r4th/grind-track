package org.grindtrack

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform