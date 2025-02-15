package ch06.data

import java.time.LocalDate

data class Invoice(
    val customer: String,
    var dueDate: LocalDate,
    val orders: List<Order>
)

data class Order(
    val amount: Int
)

data class Driver(
    val numberOfLateDeliveries: Int
)

data class Customer(
    val name: String,
    val location: String
)
