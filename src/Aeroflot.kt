
fun main(args: Array<String>) {

    val citiesNoRepetition: MutableList<Aircraft> = mutableListOf(
            Boeing747("B7471000", 10000.0, 1000.0, 500),
            AirbusA380("A380300", 12000.0, 1100.0, 700),
            An124Ruslan("Ан124100", 10000.0, 1000.0, 120.0))
    consolInsert(citiesNoRepetition)
}

fun consolInsert(citiesNoRepetition: MutableList<Aircraft>){
    loop@ while (true){
        println("""Введите номер одного из следующих действий:
        |1) Вывести колличество всех самолетов авиокомпании
        |2) Вывести параметры всех самолетов авиокомпании
        |3) Вывести параметры самолета с определенным номером номером
        |4) Выход из программы
        |Ваш ответ (введите номер действия 1-4): """.trimMargin())

        when(readLine().toString()){
            "1" -> {
                println("В парке авиокомпании ${citiesNoRepetition.size} типов самолетов")
            }
            "2" -> {
                println("Параметры типов всех самолетов:")
                citiesNoRepetition.forEach { aircraft -> aircraft.displayParametrs(); println() }
            }
            "3" -> {
                println("Введите название самолета:")
                val number = readLine().toString()
                citiesNoRepetition.filter{ aircraft -> aircraft.getNum().equals(number) }.forEach { aircraft -> aircraft.displayParametrs() }
                println("Выведены все самолеты с названием '$number'\n")
            }
            "4" -> {
                println("Программа завершает работу")
                break@loop
            }
            else -> println("Нет такого варианта ответа")
        }
    }
}

interface Passenger{
    val maxCapacity: Int
}

interface Cargo{
    val maxCarryingCapacity: Double
}

abstract class Aircraft(protected var number: String, protected var maxRangeKm: Double, protected var maxVolumeLiters: Double){
    protected val litersPer100Km: String
        get() = "${maxVolumeLiters / maxRangeKm * 100} литров на 100 км"

    open fun displayParametrs(){
        println("""Номер самолета: $number
        |Максимальная дальность полета: $maxRangeKm
        |Вместимость бака: $maxVolumeLiters
        |Расход топлива: $litersPer100Km""".trimMargin())
    }

    open fun getNum(): String{
        return number
    }
}

class Boeing747(
        number: String,
        maxRangeKm: Double,
        maxVolumeLiters: Double,
        override val maxCapacity: Int): Aircraft(number, maxRangeKm, maxVolumeLiters), Passenger{
    override fun displayParametrs() {
        super.displayParametrs()
        println("Максимальная вместимость: $maxCapacity человек")
    }
}

class AirbusA380(
        number: String,
        maxRangeKm: Double,
        maxVolumeLiters: Double,
        override val maxCapacity: Int): Aircraft(number, maxRangeKm, maxVolumeLiters), Passenger{
    override fun displayParametrs() {
        super.displayParametrs()
        println("Максимальная вместимость: $maxCapacity человек")
    }
}

class An124Ruslan(
        number: String,
        maxRangeKm: Double,
        maxVolumeLiters: Double,
        override val maxCarryingCapacity: Double): Aircraft(number, maxRangeKm, maxVolumeLiters), Cargo{
    override fun displayParametrs() {
        super.displayParametrs()
        println("Максимальная грузоподъемность: $maxCarryingCapacity тонн")
    }
}