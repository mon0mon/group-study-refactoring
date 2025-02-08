package ch04.sec02

import ch04.data.Producer
import kotlinx.serialization.Serializable

@Serializable
data class Province(
    val name: String,
    val producers: MutableList<Producer>,
    var demand: Int,
    var price: Int
) {
    val totalProduction: Int
        get() = producers.sumOf { it.production }
    val satisfiedDemand: Int
        get() = demand.coerceAtMost(totalProduction)
    val demandValue: Int
        get() = satisfiedDemand * price
    val demandCost: Int
        get() {
            var remainingDemand = demand
            var result = 0
            producers.sortBy { it.cost }
            producers.forEach { producer ->
                val contribution = remainingDemand.coerceAtMost(producer.production)
                remainingDemand -= contribution
                result += contribution * producer.cost
            }
            return result
        }
    val shortFall: Int
        get() = demand - totalProduction
    val profit: Int
        get() = demandValue - demandCost
}
