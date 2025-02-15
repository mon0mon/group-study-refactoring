package ch06.sec02

import ch06.data.Driver

private fun getRating_Old(driver: Driver): Int {
    return if (moreThanFiveLateDeliveries(driver)) 2 else 1
}

private fun getRating_Refactored(driver: Driver): Int {
    return if (driver.numberOfLateDeliveries > 5) 2 else 1
}

private fun moreThanFiveLateDeliveries(driver: Driver) = driver.numberOfLateDeliveries > 5
