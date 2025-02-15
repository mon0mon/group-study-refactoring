package ch06.sec04

import ch06.data.Order

private fun unnamedFunction_Old(anOrder: Order): Boolean {
    val basePrice = anOrder.basePrice

    return basePrice > 1_000
}

private fun unnamedFunction_Refactored(anOrder: Order): Boolean {
    return anOrder.basePrice > 1_000
}
