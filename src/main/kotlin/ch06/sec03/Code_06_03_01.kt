package ch06.sec03

import ch06.data.Order

private fun price_Old(order: Order): Double {
    return (order.quantity * order.itemPrice - 0.coerceAtLeast(order.quantity - 50)
            * order.itemPrice * 0.05 + 100.0.coerceAtMost(order.quantity * order.itemPrice * 0.1))
}

private fun price_Refactored(order: Order): Double {
    val basePrice = order.quantity * order.itemPrice
    val quantityDiscount = 0.coerceAtLeast(order.quantity - 50) * order.itemPrice * 0.05
    val shipping = 100.0.coerceAtMost(basePrice * 0.1)

    return basePrice - quantityDiscount + shipping
}
