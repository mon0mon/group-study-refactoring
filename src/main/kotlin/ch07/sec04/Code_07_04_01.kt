package ch07.sec04

data class Item (
    val price: Int
)

data class Order01(
    val quantity: Int,
    val item: Item
) {
    val price: Double
        get() {
            val basePrice = quantity * item.price
            val discountFactor = if (basePrice > 1000) 0.95 else 0.98

            return basePrice * discountFactor
        }
}

data class Order02(
    val quantity: Int,
    val item: Item
) {

    private val basePrice: Int = this.quantity * this.item.price
    private val discountFactor: Double = if (basePrice > 1000) 0.95 else 0.98

    val price: Double = this.basePrice * this.discountFactor
}
