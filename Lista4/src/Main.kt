import java.time.LocalDate
import java.time.Month
import kotlin.random.*

enum class CostType(val costType: String) {
    REFUELING("Tankowanie"),
    SERVICE("Serwis"),
    PARKING("Parking"),
    INSURANCE("Ubezpieczenie"),
    TICKET("Mandat")
}

data class Car (
    val name: String,
    val brand: String,
    val model: String,
    val yearOfProduction: Int,
    val costs: List<Cost>
)

data class Cost (
    val type: CostType,
    val date: LocalDate,
    val amount: Int
)


object DataProvider1 {
    val generalCosts = List(5) {
        Cost(
            CostType
                .values()[Random.nextInt(CostType.values().size)],
            LocalDate.of(
                2025,
                Random.nextInt(1, 13),
                Random.nextInt(1, 28)
            ),
            Random.nextInt(5000)
        )
    }
}

object DataProvider2 {

    private fun  generalCosts(size: Int) = List(size) {
        Cost(
            CostType.values()[Random.nextInt(CostType.values().size)],
            LocalDate.of(
                2025,
                Random.nextInt(1,13),
                Random.nextInt(1,28)),
            Random.nextInt(5000)
        )
    }

    val cars = listOf(
        Car("Domowy", "Skoda", "Fabia", 2002, generalCosts(100)),
        Car("Służbowy", "BMW", "Coupe", 2015, generalCosts(50)),
        Car("Kolekcjonerski", "Fiat", "125p", 1985, generalCosts(10)),
    )
}

//Zad 1
fun groupedCostMap(costs: List<Cost>): Map<Month, List<Cost>>{
    return costs
        .groupBy{it.date.month}
        .mapValues {entry ->
            entry.value.sortedBy{it.date}
        }
        .toSortedMap()
}

//Zad 2
fun printGroupedCosts(costs: List<Cost>){
    costs
        .groupBy{it.date.month}
        .toSortedMap()
        .forEach{(month, costList) ->
            println(month.name)
            costList
                .sortedBy{it.date}
                .forEach{
                    val day = it.date.dayOfMonth.toString().padStart(2, '0')
                    println(" $day ${it.type.name} ${it.amount} zł")
                }
        }
}

//Zad3
fun getCarCosts(carName: String): List<Pair<CostType, Int>>{
    val car = DataProvider2.cars.find{it.name == carName} ?:return emptyList()

    return CostType.values()
        .map{ type ->
            val total = car.costs
                .filter {it.type == type}
                .sumOf{it.amount}
            type to total
        }
        .sortedByDescending{it.second}
}

fun printCarCosts(costs: List<Pair<CostType, Int>>){
    costs.forEach{(type, amount) ->
        println("${type.name} ${amount} zł")
    }
}

//Zad4
fun getFullCosts (cars: List<Car>): Map<CostType, Int>{
    return CostType.values().associateWith { type ->
        cars.flatMap {it.costs}
            .filter {it.type == type}
            .sumOf{it.amount}
    }
}

fun printFullCost(costMap: Map<CostType, Int>) {
    costMap.forEach { (type, amount) ->
        val label = type.name
        println("$label $amount")
    }
}

fun main() {

    println("--------------------Zad1--------------------")
    val grouped = groupedCostMap(DataProvider1.generalCosts)
    println(grouped)

    println(" ")
    println("--------------------Zad2--------------------")
    val koszty = printGroupedCosts(DataProvider1.generalCosts)
    println(koszty)

    println(" ")
    println("--------------------Zad3--------------------")
    val domowyCosts = getCarCosts("Domowy")
    printCarCosts(domowyCosts)
    println(" ")
    val sluz = getCarCosts("Służbowy")
    printCarCosts(sluz)
    println(" ")
    val kol = getCarCosts("Kolekcjonerski")
    printCarCosts(kol)


    println(" ")
    println("--------------------Zad4--------------------")
    val total = getFullCosts(DataProvider2.cars)
    printFullCost(total)

}