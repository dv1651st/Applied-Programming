package com.example.rockpaperscissors.models

data class RockPaperScissorsImages(
    val image: Int,
    val identifier: String,
    var random: Boolean = false,
    var isSelected: Boolean = false
)