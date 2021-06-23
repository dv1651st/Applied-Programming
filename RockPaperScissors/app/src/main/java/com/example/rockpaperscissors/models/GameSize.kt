package com.example.rockpaperscissors.models

enum class GameSize (val numCards: Int) {
    REGULAR(3),
    RANDOM(4);

    fun getWidth(): Int {
        return when (this) {
            REGULAR -> 1
            RANDOM -> 2
        }
    }
    fun getHeight(): Int {
        return when (this) {
            REGULAR -> 3
            RANDOM -> 2
        }
//        return numCards / getWidth()
    }
}