package ch06.data

import java.time.LocalDate

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
    val location: String
)
