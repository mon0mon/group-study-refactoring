package ch07.sec02

/**
 * 소개
 */

data class Person01(
    var courses: List<Course> = listOf()
)

data class Course(
    val name: String,
    val isAdvanced: Boolean
)

class Person02(
    courses: MutableList<Course> = mutableListOf()
) {
    private val _courses = courses

    val courses: List<Course>
        get() = _courses.toList()

    fun addCourse(course: Course) = _courses.add(course)
    fun removeCourse(course: Course) = _courses.remove(course)
}
