package ch06.sec10

import ch04.data.json
import kotlinx.serialization.Serializable

private const val readingJson: String = """
    {
        customer: "ivan",
        quantity: 10,
        month: 5,
        year: 2017
    }
"""

class unnamedClass_Old {
    fun main() {
        val aReading = acquireReading()
        val base = baseRate(month = aReading.month, year = aReading.year) * aReading.quantity
        val texableCharge = 0.0.coerceAtMost(base - taxThreshold(aReading.year))
    }

    private fun acquireReading() = json.decodeFromString(Reading.serializer(), readingJson)
    private fun baseRate(month: Int, year: Int): Double = TODO("Not Implemented")
    private fun taxThreshold(year: Int): Double = TODO("Not Implemented")

    private fun calculateBaseCharge(aReading: Reading): Double {
        return baseRate(month = aReading.month, year = aReading.year) * aReading.quantity
    }
}

class unnamedClass_Refactored {
    fun main() {
        val rawReading = acquireReading()
        val aReading = enrichReading(rawReading)
        val texableCharge = aReading.texableCharge
    }

    private fun acquireReading() = json.decodeFromString(Reading.serializer(), readingJson)
    private fun baseRate(month: Int, year: Int): Double = TODO("Not Implemented")
    private fun taxThreshold(year: Int): Double = TODO("Not Implemented")

    private fun calculateBaseCharge(aReading: Reading): Double {
        return baseRate(month = aReading.month, year = aReading.year) * aReading.quantity
    }

    private fun enrichReading(original: Reading): Reading {
        val result = original.copy()

        result.baseCharge = calculateBaseCharge(result)
        result.texableCharge = 0.0.coerceAtMost(result.baseCharge - taxThreshold(result.year))

        return result
    }
}

@Serializable
data class Reading(
    val customer: String,
    val quantity: Int,
    val month: Int,
    val year: Int
) {
    var baseCharge: Double = 0.0
    var texableCharge: Double = 0.0
}
