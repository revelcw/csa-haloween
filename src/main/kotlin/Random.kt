//package org.example
//
//
//import com.varabyte.kotter.foundation.session
//import com.varabyte.kotter.foundation.text.textLine
//import java.awt.Point
//import java.util.*
//
//
//const val SPACE = " "
//fun Int.spacing() = SPACE.repeat(this)
//fun String.beginning() = "\r".repeat(this.lines().size)
//
//
//class Cell(val active: Boolean = false) {
//    override fun toString(): String {
//        return if (active) "■" else " "
//    }
//}
//
//object Direction {
//    val Up = Point(0, 1)
//    val Down = Point(0, -1)
//    val Left = Point(-1, 0)
//    val Right  = Point(1, 0)
//}
//
//data class Point(val x: Int, val y: Int) {
//    fun translated(delta: Point): Point = this + delta
//
//    operator fun plus(that: Point): Point = Point(this.x + that.x, this.y + that.y)
//    operator fun minus(that: Point): Point = Point(this.x - that.x, this.y - that.y)
//}
//
//
//data class Board(val width: Int, val height: Int) {
//    val board: Array<Array<Cell?>> = Array(height) { Array(width) { Cell() } }
//
//    val cursor = Point(width / 2, height / 2)
//
//    companion object {
//        const val H_SPACING = 2
//    }
//
//
//    override fun toString(): String {
//        val output = mutableListOf<String>();
//
//
//        output.add("-" + (H_SPACING).spacing() + (1..width).joinToString(H_SPACING.spacing()))
//
//
//        output.addAll(board.mapIndexed { index, item ->
//            "${index + 1}${H_SPACING.spacing()}" + item.joinToString(H_SPACING.spacing()) {
//
//            }
//        })
//        return output.joinToString("\n")
//    }
//
//
//
//
//}
//
//
////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//fun main() {
//    val scanner = Scanner(System.`in`)
//
//    val x = Board(8, 8);
//
//    session {
//        section {
//            textLine(x.toString())
//        }.run()
//    }
//
//
//    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
//
//
//
//
//    // to see how IntelliJ IDEA suggests fixing it.
//
//
//
//
//}
//
