package hangman

val HangmanStates = listOf(
    "", """ ╭───╮ 
 ╰───╯
""",
    """ ╭───╮ 
 ╰─┬─╯
   │
   │
   │
""",
    """ ╭───╮ 
 ╰─┬─╯
───┤
   │
   │
""",
    """ ╭───╮ 
 ╰─┬─╯
───┼───
   │
   │
""",
    """ ╭───╮ 
 ╰─┬─╯
───┼───
   │
   │
  ╱
 /  
""",
    """ ╭───╮ 
 ╰─┬─╯
───┼───
   │
   │
  ╱ ╲
 /   \
"""
)

val MAX_MISTAKES = HangmanStates.size - 1