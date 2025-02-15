package ch06.sec03

import ch06.data.Order

/**
 * 6.3 변수 추출하기
 * 예시: 클래스 안에서
 */
private fun price_Old(order: Order): Double {
    //  가격(price) = 기본 가격 - 수량 할인 + 배송비
    return (order.quantity * order.itemPrice - 0.coerceAtLeast(order.quantity - 50)
            * order.itemPrice * 0.05 + 100.0.coerceAtMost(order.quantity * order.itemPrice * 0.1))
}

private fun price_Refactored(order: Order_Refacotred): Double {
    return order.price
}

data class Order_Old(
    val quantity: Int,
    val itemPrice: Int
) {
    val price: Double
        get() {
            return (this.quantity * this.itemPrice - 0.coerceAtLeast(this.quantity - 50)
                    * this.itemPrice * 0.05 + 100.0.coerceAtMost(this.quantity * this.itemPrice * 0.1))
        }
}

data class Order_Refacotred(
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

