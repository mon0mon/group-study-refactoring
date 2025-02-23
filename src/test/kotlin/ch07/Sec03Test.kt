package ch07

import ch07.sec03.Order01
import ch07.sec03.Order02
import ch07.sec03.OrderPriority
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Sec03Test {

    @Test
    fun `리팩터링 이전 코드 테스트`() {
        val order1 = Order01("normal")
        val order2 = Order01("high")
        val order3 = Order01("rush")

        val orders = listOf(order1, order2, order3)

        val filtered = orders.filter { it.priority == "high" || it.priority == "rush" }

        assertThat(filtered).containsExactly(order2, order3)
    }

    @Test
    fun `리팩터링 이후 코드 테스트`() {
        val order1 = Order02(OrderPriority.NORMAL)
        val order2 = Order02(OrderPriority.HIGH)
        val order3 = Order02(OrderPriority.RUSH)

        val orders = listOf(order1, order2, order3)

        val filtered = orders.filter { it.priority > OrderPriority.NORMAL }

        assertThat(filtered).containsExactly(order2, order3)
    }
}
