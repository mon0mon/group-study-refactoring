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
