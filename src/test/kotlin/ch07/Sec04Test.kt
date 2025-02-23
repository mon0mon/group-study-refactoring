package ch07

import ch07.sec04.Item
import ch07.sec04.Order01
import ch07.sec04.Order02
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Sec04Test {

    @Test
    fun `리팩터링 이전 코드 테스트`() {
        val item = Item(1000)
        val item2 = Item(2000)

        val order = Order01(1, item)
        val order2 = Order01(10, item2)

        assertThat(order.price).isEqualTo(980.0)
        assertThat(order2.price).isEqualTo(19000.0)
    }

    @Test
    fun `리팩터링 이후 코드 테스트`() {
        val item = Item(1000)
        val item2 = Item(2000)

        val order = Order02(1, item)
        val order2 = Order02(10, item2)

        assertThat(order.price).isEqualTo(980.0)
        assertThat(order2.price).isEqualTo(19000.0)
    }
}
