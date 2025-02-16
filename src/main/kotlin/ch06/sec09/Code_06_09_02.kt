package ch06.sec09

import ch04.data.json
import ch06.data.Reading

private const val readingJson: String = """
    {
        customer: "ivan",
        quantity: 10,
        month: 5,
        year: 2017
    }
"""

private fun acquireReading() = json.decodeFromString(Reading.serializer(), readingJson)
private fun baseRate(month: Int, year: Int): Double = TODO("Not Implemented")
private fun taxThreshold(year: Int): Double = TODO("Not Implemented")

private val aReading = acquireReading()
private val base = baseRate(month = aReading.month, year = aReading.year) * aReading.quantity
private val texableCharge = 0.0.coerceAtMost(base - taxThreshold(aReading.year))

private fun calculateBaseCharge(aReading: Reading): Double {
    return baseRate(month = aReading.month, year = aReading.year) * aReading.quantity
}
