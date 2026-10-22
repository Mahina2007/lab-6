package task13

sealed interface Passenger {
    val name: String
}

data class Regular(override val name: String, val age: Int) : Passenger
data class Student(override val name: String, val university: String) : Passenger
data object Senior : Passenger {
    override val name: String = "Senior Citizen"
}

fun calculateFare(passenger: Passenger, baseFare: Double): Double = when (passenger) {
    is Regular -> if (passenger.age < 7) 0.0 else baseFare
    is Student -> baseFare * 0.5
    Senior -> 0.0
}

fun main() {
    val passengers: List<Passenger> = listOf(
        Regular("Alice", 25),
        Regular("Child", 5),
        Student("Bob", "TUIT"),
        Senior
    )
    val base = 3000.0
    passengers.forEach { p ->
        println("${p.name} pays: ${calculateFare(p, base)} UZS")
    }
}