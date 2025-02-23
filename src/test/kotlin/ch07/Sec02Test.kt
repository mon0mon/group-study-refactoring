package ch07

import ch07.sec02.Course
import ch07.sec02.Person02
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Sec02Test {

    @Test
    fun test() {
        val java = Course("java", false)
        val kotlin = Course("kotlin", true)
        val python = Course("python", false)

        val student = Person02(mutableListOf(java, kotlin))

        val courses = student.courses

        student.addCourse(python)
        student.removeCourse(java)

        Assertions.assertThat(student.courses).containsExactly(kotlin, python)
        Assertions.assertThat(courses).containsExactly(java, kotlin)
        Assertions.assertThat(courses).isNotEqualTo(student.courses)
    }
}
