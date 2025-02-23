package ch07

import ch07.sec06.Shipment01
import ch07.sec06.Shipment02
import ch07.sec06.TrackingInformation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Sec06Test {

    @Test
    fun `리팩터링 이전 코드 테스트`() {
        val shipment01 = Shipment01(TrackingInformation(shippingCompany = "amazon", trackingNumber = "011-110"))

        assertThat(shipment01.trackingInfo()).isEqualTo("amazon: 011-110")
    }

    @Test
    fun `리팩터링 이후 코드 테스트`() {
        val shipment02 = Shipment02(shippingCompany = "amazon", trackingNumber = "011-110")

        assertThat(shipment02.trackingInfo()).isEqualTo("amazon: 011-110")
    }
}
