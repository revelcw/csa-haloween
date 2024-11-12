package org.example


import Bathroom
import DiningRoom
import Game
import Kitchen
import Lobby
import hangman.Hangman
import java.util.*
import kotlin.reflect.KClass

fun main() {
    val words = listOf("skeleton", "candy", "halloween", "pumpkin", "zombie", "scary").shuffled()

    val game = Game(
        listOf(
            Lobby(words.take(3)),
            DiningRoom(words[0]),
            Kitchen(words[1]),
            Bathroom(words[2])
        )
    )



    game.start()
}

