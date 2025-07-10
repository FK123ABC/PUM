// Zad 1
val r = { a: Int, b: String ->
    var c: String = ""
    for (i in a downTo 1) {
        c = c + b
    }
    println(c)
}


// Zad 2
val <T> List<T>.tail: List<T>
    get() =  this.drop(1)

val <T> List<T>.head: T
    get() = this.first()


// Zad 3
fun <A> isSorted(lst: List<A>, order: (A, A) -> Boolean): Boolean {
    return lst.zipWithNext().all { (a, b) -> order(a, b) }
}


// Zad 4
fun <A> tailToHead(lst: List<A>): List<A> {
    if (lst.isEmpty()) throw IllegalStateException("Pusta lista")
    return listOf(lst.last()) + lst.dropLast(1)
}


// Zad 5
fun <A> setHead(lst: List<A>, item: A): List<A> {
    return listOf(item) + lst.drop(1)
}


// Zad 6
fun check(N: Int, list: List<Int>): Int {
    for (i in N until list.size) {
        val preamble = list.subList(i - N, i).toSet()
        val current = list[i]
        if (!preamble.any { num -> (current - num) in preamble && num != (current - num) }) {
            return current
        }
    }
    return -1
}


fun main() {
    println(" ")
    println("--- Zadanie 1 ---")
    println (r(5, "a"))

    println(" ")
    println("--- Zadanie 2 ---")
    val listExample = listOf(1, 2, 3)
    println("Tail: ${listExample.tail}")
    println("Head: ${listExample.head}")

    println(" ")
    println("--- Zadanie 3 ---")
    println(isSorted(listOf(1, 2, 3, 4)) { i: Int, j: Int -> i < j })
    println(isSorted(listOf("ahyyhh", "bkjn", "cnn", "duu")) { i: String, j: String -> i.first() < j.first() })

    println(" ")
    println("--- Zadanie 4 ---")
    println(tailToHead(listOf(1, 2, 3)))

    println(" ")
    println("--- Zadanie 5 ---")
    println(setHead(listOf(1, 2, 3), 5))

    println(" ")
    println("--- Zadanie 6 ---")
    println(check(3, listOf(1, 2, 3, 5, 7, 12, 30, 37, 31)))
}
