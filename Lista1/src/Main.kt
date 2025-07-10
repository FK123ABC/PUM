fun zad1a(liczba: Int): Int
{
    var i = 1
    var a = ""
    while (i <= liczba)
    {
        if (i % 3 == 0)
        {
            a = a + "trzy"
        }
        if (i % 5 == 0)
        {
            a = a + "piec"
        }
        if (i % 7 == 0)
        {
            a = a + "siedem"
        }
        if (i % 11 == 0)
        {
            a = a + "jedenascie"
        }
        if (i % 13 == 0)
        {
            a = a + "trzynascie"
        }
        if (i % 3 != 0 && i % 5 != 0 && i % 7 != 0 && i % 11 != 0 && i % 13 != 0)
        {
            println(i)
        } else {
            println(a)
        }
        a = ""
        i++
    }
    return liczba
}

fun zad2(word: String): Boolean
{
    return word == word.reversed()
}

fun zad31(rows: Int): List<List<Int>>
{
    val tr = mutableListOf<List<Int>>()
    for (i in 0 until rows)
    {
        val row = MutableList(i + 1) { 1 }
        for (j in 1 until i)
        {
            row[j] = tr[i - 1][j - 1] + tr[i - 1][j]
        }
        tr.add(row)
    }
    return tr
}

fun zad32(triangle: List<List<Int>>, rows: Int)
{
    val maxWidth = rows * 2
    for (row in triangle)
    {
        val formattedRow = row.joinToString(" ")
        val padding = " ".repeat((maxWidth - formattedRow.length) / 2)
        println(padding + formattedRow)
    }
}

fun zad4(n: Int)
{

    var sum = 1

    for (i in 2 until n)
    {
        if (n % i == 0)
        {
            sum += i
        }
    }
    if (sum == n)
    {
        println("Liczba jest doskonała");
    }
    else if(sum >= n)
    {
        println("Podana liczba jest obfita");
    }
    else if (sum < n)
    {
        println("Podana liczba jest niedomiarowa");
    }
}

//fun Armstrong(p: Int)
//{
//   val c: Int = p;
//    println(c)
//    val z: List <Int> = list(c)

//}
fun main(args: Array<String>)
{
    val num: Int = 15;
    println(zad1a(num))


    println(" ")
    println(" ")
    val word = "abba"
    println(zad2(word))


    println(" ")
    println(" ")
    val wys = 5
    val triangle = zad31(wys)
    zad32(triangle, wys)


    println(" ")
    println(" ")
    val czt: Int = 8;
    println( zad4(czt))


    println(" ")
    println(" ")
    val p: Int = 128
    //   println(Armstrong(p))
}