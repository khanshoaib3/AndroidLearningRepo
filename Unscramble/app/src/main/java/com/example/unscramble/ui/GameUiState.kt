package com.example.unscramble.ui

data class GameUiState(
    val currentScrambledWord: String = "scrambleun",
    val currentWordCount: Int = 1,
    val isGuessWordWrong: Boolean = true,
    val score: Int = 0,
    val isGameOver: Boolean = false
)
