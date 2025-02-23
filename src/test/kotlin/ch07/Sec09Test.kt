package ch07

import ch07.sec09.UnnamedClass
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Sec09Test {

    @Test
    fun `리팩터링 이전 코드 테스트`() {
        val unnamedClass = UnnamedClass()

        assertThat(unnamedClass.foundPerson01("Don")).isEqualTo("Don")
    }

    @Test
    fun `리팩터링 이후 코드 테스트`() {
        val unnamedClass = UnnamedClass()

        assertThat(unnamedClass.foundPerson02("Don")).isEqualTo("Don")
    }
}
