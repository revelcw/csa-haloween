package hangman

class Hangman(private val word: String) {
    private var mistakes: Int = 0

    private val letters = "abcdefghijklmnopqrstuvwxyz";

    private val guessedLetters = mutableListOf<Char>()

    var gameOver = false
        private set;

    var won = false
        private set;

    private fun hasWon(): Boolean = word.all { guessedLetters.contains(it) }
    fun isValidLetter(char: Char) = letters.contains(char)
    private fun getWrongLetters(): List<Char> = guessedLetters.filter { !word.contains(it) }

    fun guess(char: Char) {
        if (mistakes >= MAX_MISTAKES) return

        if (!isValidLetter(char)) throw IllegalStateException()

        guessedLetters.add(char)

        if (!word.contains(char)) {
            mistakes++
        }

        if (hasWon()) {won = true; gameOver = true}
        if (mistakes == MAX_MISTAKES) gameOver = true


//        if (hasWon()) won = true;
    }
    fun render() {
        println("───┐")
        println(HangmanStates[mistakes])

        val blanks = word.map {
            val char = if (guessedLetters.contains(it)) it.uppercaseChar() else " "
            " ${Font.underline} ${char} ${Font.base}"
        }.joinToString(" ")

        println(blanks)

        println("Wrong letters: ${getWrongLetters().joinToString(", ")}")
    }
}