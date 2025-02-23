package ch07

import ch07.sec08.Department
import ch07.sec08.Person01
import ch07.sec08.Person02
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Sec08Test {

    @Test
    fun `리팩터링 이전 코드 테스트`() {
        val person01 = Person01("김철수", Department("A1", "김부장"))

        Assertions.assertThat(person01.manager).isEqualTo("김부장")
    }

    @Test
    fun `리팩터링 이후 코드 테스트`() {
        val person02 = Person02("김철수", Department("A1", "김부장"))

        Assertions.assertThat(person02.department.manager).isEqualTo("김부장")
    }
}
