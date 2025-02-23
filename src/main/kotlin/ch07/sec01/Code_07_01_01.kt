package ch07.sec01

object Organization_01 {
    val name = "애크미 구스베리"
    val country = "GB"
}

class Organization_02(
    val name: String,
    val country: String
) {
    val greetings: String
        get() = "Hello, I'm $name live in $country!"
}
