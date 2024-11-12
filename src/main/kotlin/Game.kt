import java.util.*
import kotlin.reflect.KClass

class Game(val rooms: List<Room>) {
    var finished: Boolean = false
        private set

    val scanner = Scanner(System.`in`)

    fun tell(string: String): Game {
        print("$string")
//        print("(enter)")
        scanner.nextLine()
        return this;
    }

    init {
        rooms.forEach { it.game = this }
    }

    var currentRoom = rooms.first()

    inline fun <reified T : Room> visit(clazz: KClass<T>) {
        visit(room(clazz))
    }

    fun visit(room: Room) {
        currentRoom = room
        room.visit()
    }

    inline fun <reified T : Room> getRoom(): T = rooms.filterIsInstance<T>().first()
    inline fun <reified T : Room> room(clazz: KClass<T>): T = rooms.filterIsInstance<T>().first()

    fun start() {
        currentRoom.visit()
    }

    fun end() {
        finished = true
        scanner.close()
    }
}
