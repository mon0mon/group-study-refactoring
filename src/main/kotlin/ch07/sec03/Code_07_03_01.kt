package ch07.sec03

enum class OrderPriority {
    NORMAL, HIGH, RUSH
}

data class Order01(
    val priority: String
)

data class Order02(
    val priority: OrderPriority
) {
    operator fun compareTo(orderPriority: OrderPriority): Int {
        return priority.compareTo(orderPriority)
    }
}
