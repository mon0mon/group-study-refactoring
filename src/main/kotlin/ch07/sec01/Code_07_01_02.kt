package ch07.sec01

import kotlinx.serialization.Serializable

@Serializable
data class ClientInformation(
    val name: String,
    val id: Int,
    val usages: MutableMap<String, MutableMap<String, Int>>
) {
    fun getUsages(year: String, month: String): Int? {
        return usages[year]?.get(month)
    }

    fun setUsages(year: String, month: String, value: Int) {
        usages.computeIfAbsent(year) { mutableMapOf() }[month] = value
    }

    fun compareUsage(laterYear: String, month: String, amount: Int): CompareUsage {
        val later = getUsages(laterYear, month)
        val earlier = getUsages((laterYear.toInt() - 1).toString(), month)

        return if (later != null && earlier != null) {
            CompareUsage(laterAmount = later, change = later - earlier)
        } else {
            CompareUsage()
        }
    }
}

class CompareUsage(
    val laterAmount: Int? = null,
    val change: Int? = null
)

