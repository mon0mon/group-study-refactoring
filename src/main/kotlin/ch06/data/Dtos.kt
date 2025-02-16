package ch06.data

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

data class Invoice(
    val customer: String,
    var dueDate: LocalDate,
    val orders: List<Order>
)

data class Order(
    val amount: Int,
    val quantity: Int,
    val itemPrice: Int
) {
    val basePrice: Int
        get() = this.quantity * this.itemPrice
    val quantityDiscount: Double
        get() = 0.coerceAtLeast(this.quantity - 50) * this.itemPrice * 0.05
    val shipping: Double
        get() = 100.0.coerceAtMost(this.basePrice * 0.1)
    val price: Double
        get() = this.basePrice - this.quantityDiscount + this.shipping
}

data class Driver(
    val numberOfLateDeliveries: Int
)

data class Customer(
    val name: String,
    val location: String,
    val address:  Address
)

data class Address(
    val state: String
)

data class Person(
    val firstName: String,
    val lastName: String
)

data class DateRange(
    val startDate: LocalDate,
    val endDate: LocalDate
)

@Serializable
data class Station(
    val name: String,
    val readings: List<TemperatureData>
)

@Serializable
data class TemperatureData (
    val temp: Int,
    @Contextual
    val time: LocalDateTime
)

data class NumberRange(
    val min: Int,
    val max: Int
) {
    fun contains(value: Int): Boolean = (value >= this.min) && (value <= this.max)
}
