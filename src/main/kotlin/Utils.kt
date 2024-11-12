object Font {
    private fun font(int: Int = 0) = 27.toChar().toString() + "[${int}m"
    val underline = font(4)
    val base = font()
}