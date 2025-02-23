package ch07.sec06

class Person01(
    var name: String,
    telephoneNumber: TelephoneNumber
) {
        private val _telephoneNumber = telephoneNumber

        val telephoneNumber: String
        get() = _telephoneNumber.toString()
}

data class Person02(
    var name: String,
    var officeAreaCode: String,
    var officeNumber: String
) {

    val telephoneNumber: String
        get() = "($officeAreaCode) $officeNumber"
}

data class TelephoneNumber(
    var areaCode: String,
    var number: String
) {

    override fun toString() = "($areaCode) $number"
}
