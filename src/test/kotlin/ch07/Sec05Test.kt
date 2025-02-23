package ch07

import ch07.sec05.Person01
import ch07.sec05.Person02
import ch07.sec05.TelephoneNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Sec05Test {
    @Test
    fun `리팩터링 이전 코드 테스트`() {
        val person01 = Person01("John", "02", "1234")

        assertThat(person01.telephoneNumber).isEqualTo("(02) 1234")
    }

    @Test
    fun `리팩터링 이후 코드 테스트`() {
        val person02 = Person02("John", TelephoneNumber("02", "1234"))

        assertThat(person02.telephoneNumber).isEqualTo("(02) 1234")
    }
}
