// Zadanie 1: Znajdowanie duplikatów
fun findDuplicates(lst: List<Int>): List<Int> {
    return lst.groupingBy { it }.eachCount().filter { it.value > 1 }.keys.sorted()
}

// Zadanie 2: Tworzenie mapy Int -> Boolean
fun addToBoolean(): Map<Int, Boolean> {
    return (1..20).associateWith { it % 2 == 0 }
}

// Zadanie 3: Sumowanie dodatnich liczb
fun suma(a: List<Int>): Int {
    return a.filter { it > 0 }.sum()
}

// Zadanie 4: Zliczanie elementów w zagnieżdżonych listach
fun countElements(lst: List<List<String>>): Map<String, Int> {
    return lst.flatten().groupingBy { it }.eachCount()
}

// Zadanie 5: Podnoszenie do kwadratu dodatnich liczb na nieparzystych indeksach
fun evenPositiveSquare(lst: List<Int>): List<Int> {
    return lst.mapIndexedNotNull { index, value -> if (index % 2 == 1 && value > 0) value * value else null }
}

// Zadanie 6: Generowanie permutacji listy
fun <T> perm(lst: List<T>): List<List<T>> {
    if (lst.size <= 1) return listOf(lst)
    return lst.flatMap { item -> perm(lst - item).map { listOf(item) + it } }
}

// Zadanie 7: Grupowanie i sortowanie słów
fun srt(lst: List<String>): List<Pair<Char, List<String>>> {
    return lst.filter { it.length % 2 == 0 }
        .groupBy { it.first() }
        .map { it.key to it.value.sorted() }
        .sortedBy { it.first }
}

// Zadanie 8: Konwersja słowa na numer telefonu
fun convert(word: String): String {
    val map = mapOf(
        'a' to "2", 'b' to "22", 'c' to "222",
        'd' to "3", 'e' to "33", 'f' to "333",
        'g' to "4", 'h' to "44", 'i' to "444",
        'j' to "5", 'k' to "55", 'l' to "555",
        'm' to "6", 'n' to "66", 'o' to "666",
        'p' to "7", 'q' to "77", 'r' to "777", 's' to "7777",
        't' to "8", 'u' to "88", 'v' to "888",
        'w' to "9", 'x' to "99", 'y' to "999", 'z' to "9999"
    )
    return word.lowercase().map { map[it] ?: "" }.joinToString("")
}

// Zadanie 9: Analiza wyników studentów
data class StudentScore(val name: String, val subject: String, val score: Int)

fun analyzeResults(students: List<StudentScore>): Triple<Map<String, List<StudentScore>>, List<StudentScore>, List<String>> {
    val passed = students.filter { it.score >= 50 }.groupBy { it.subject }
    val failed = students.filter { it.score < 50 }
    val subjectsAllPassed = passed.keys.filter { subject -> students.count { it.subject == subject } == passed[subject]?.size }
    return Triple(passed, failed, subjectsAllPassed)
}

// Zadanie 10: Operatory dla klasy Point
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    operator fun plus(value: Int) = Point(x + value, y + value)
    operator fun minus(other: Point) = Point(x - other.x, y - other.y)
    operator fun times(other: Point) = Point(x * other.x, y * other.y)
    operator fun inc() = Point(x + 1, y + 1)
    operator fun dec() = Point(x - 1, y - 1)
    operator fun not() = Point(-x, -y)
}

fun main() {
    println("\nZadanie 1: ")
    val lst = listOf(0, 1, 1, 1, 4, 4, 4, 9, 3, 3, 3, 3, 3, 3)
    println("Duplicates: " + findDuplicates(lst))

    println("\nZadanie 2: ")
    println("Boolean Map: " + addToBoolean())

    println("\nZadanie 3: ")
    val numbers = listOf(1, -4, 12, 0, -3, 29, -150)
    println("Sum of positives: " + suma(numbers))

    println("\nZadanie 4: ")
    val words = listOf(listOf("a", "b", "c"), listOf("c", "d", "f"), listOf("d", "f", "g"))
    println("Element count: " + countElements(words))

    println("\nZadanie 5: ")
    val nums = listOf(1, 2, 3, 5, -6, -1, -1, 2, 3)
    println("Even index positive squares: " + evenPositiveSquare(nums))

    println("\nZadanie 6: ")
    println("Permutations: " + perm(listOf(1, 2, 3)))

    println("\nZadanie 7: ")
    val wordsList = listOf("cherry", "blueberry", "citrus", "apple", "apricot", "banana", "coconut")
    println("Sorted and grouped words: " + srt(wordsList))

    println("\nZadanie 8: ")
    println("Converted word to number: " + convert("franek"))

    println("\nZadanie 9: ")
    val students = listOf(
        StudentScore("Alice", "Math", 78),
        StudentScore("Bob", "Math", 45),
        StudentScore("Charlie", "Physics", 92),
        StudentScore("Dave", "Physics", 55),
        StudentScore("Eve", "Physics", 40),
        StudentScore("Frank", "CS", 60),
        StudentScore("Grace", "CS", 80)
    )
    val (passedBySubject, failed, subjectsAllPassed) = analyzeResults(students)
    println("Passed students by subject: $passedBySubject")
    println("Failed students: $failed")
    println("Subjects where all passed: $subjectsAllPassed")

    println("\nZadanie 10: ")
    val p1 = Point(1, 1)
    val p2 = Point(2, 2)
    println("p1 + p2: ${p1 + p2}")
    println("p1 + 1: ${p1 + 1}")
    println("p1 - p2: ${p1 - p2}")
    println("p1 * p2: ${p1 * p2}")
    println("p1++: ${p1.inc()}")
    println("p1--: ${p1.dec()}")
    println("!p1: ${!p1}")
}
