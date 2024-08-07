package com.freelanxx.quickie.game.quickie2048.domain.model

data class GameData(
    var score: Int = 0,
    var over: Boolean = false,
    var won: Boolean = false,
)