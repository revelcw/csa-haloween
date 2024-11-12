import hangman.Hangman
import java.util.*
import kotlin.reflect.KClass

abstract class Room() {
    abstract val name: String
    lateinit var game: Game
    var visited = false
        protected set

    fun query(
        rooms: List<Room> = emptyList(),
        other: List<String> = emptyList(),
        otherCallback: Room.(String) -> Boolean = { true },
    ) {
        println("\nWhat would you like to do?")
        val roomsMap = rooms.associateBy { "enter ${it.name.lowercase()}" }

        val roomsString = roomsMap.keys.joinToString(", ")

        val otherString = other.joinToString(", ")

        val separator = if (rooms.isEmpty() || other.isEmpty()) "" else " | "

        val optionsString = roomsString + separator + otherString

        while (true) {
            println("[$optionsString]")
            print("$> ")
            val response = game.scanner.nextLine()
            val responseClean = response.trim().lowercase()

            val room = roomsMap.get(responseClean)

            if (room != null) {
                room.visit()
                return
            } else if (other.contains(responseClean)) {
                if (this.otherCallback(responseClean)) {
                    return
                } else {
                    continue;
                }
            }
            println("Please enter a valid choice!")
        }
    }

    fun visit() {
        onVisit()
        visited = true
    }

    protected abstract fun onVisit()
}


abstract class HangmanRoom() : Room() {
    abstract val word: String

    //    override val name = "dining room"
    private var wonGame = false;
    override fun onVisit() {
        if (wonGame) {
            game.tell("You have already won this game, and the word is $word!")
            game.tell("You turn to leave the room and head back to the lobby.")
            return game.visit(game.getRoom<Lobby>())
        }

        game.tell("As you walk in, you see an evil corpse hanging, and he says to you: \"Let's play a game of hangman!\"")

        val hangman = Hangman(word)

        while (!hangman.gameOver) {
            hangman.render()
            print("Guess a letter:")
            val letter = runCatching { game.scanner.nextLine().lowercase()[0] }.getOrNull()
            if (letter == null || !hangman.isValidLetter(letter)) {
                println("Please provide a valid letter!")
                continue;
            }

            hangman.guess(letter)
        }

        hangman.render()

        if (hangman.won) {
            wonGame = true
            game.tell("You successfully got your keyword, ${word.uppercase()}!")
            return query(listOf(game.getRoom<Lobby>()))
        } else {
            game.tell("The skeleton comes to life, and jumps forward, slashing you with his sword. You die bleeding out on the ground. GAME OVER.")
            return game.end()
        }
    }
}

class DiningRoom(override val word: String) : HangmanRoom() {
    //    override val word = "skeleton"
    override val name = "dining room"
}

class Kitchen(override val word: String) : HangmanRoom() {
    //    override val word = "candy"
    override val name = "kitchen"
}

class Bathroom(override val word: String) : HangmanRoom() {
    //    override val word = word
    override val name = "bathroom"
}

class Lobby(private val words: List<String>) : Room() {
    override val name = "lobby"
    override fun onVisit() {
//        println(visited)
        if (!visited) {
            game.tell("You open your eyes, sit up, dazed, and look around. You wonder where you are as you rub your head. It hurts. (always press enter)")
            game.tell("You dart your eyes around, you're in a room that looks like an entryway.")
            game.tell("It's dark and dusty, it doesn't look like anyone has lived here in a while.")
            game.tell("You stand up, and look around as your eyes adjust to the darkness.")
        } else {
            game.tell("You walk back into the lobby. Go to more rooms or try a password!")
        }
        visited = true
        query(listOf(game.getRoom<DiningRoom>(), game.getRoom<Bathroom>(), game.getRoom<Kitchen>()), listOf("try password")) {
            when (it) {
                "try password" -> {
                    println("Enter three words separated by a comma: ex. (apple,banana,rice):\n")
                    val wordsString = game.scanner.nextLine()

                    println(wordsString)

                    val guessedWords = wordsString.split(",")

                    val won = guessedWords.all { words.contains(it.trim().lowercase()) }

                    println(guessedWords)
                    println(words)

                    if (won) {
                        game.tell("You feel the lock click and the door swing open, freedom!")
                        game.end()
                        return@query true
                    } else {
                        game.tell("The door doesn't move, the combination is wrong!")
                        return@query false
                    }
                }
                else -> false
            }
        }
    }
}