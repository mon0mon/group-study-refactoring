package ch07.sec07

data class Department(
    val chargeCode: String,
    val manager: String
)

data class Person01(
    val name: String,
    val department: Department
)

data class Person02(
    val name: String,
    private val department: Department
) {
    val manager: String
        get() = department.manager
}
