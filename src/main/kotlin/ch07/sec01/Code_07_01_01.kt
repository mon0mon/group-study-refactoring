package ch07.sec01

object Organization01 {
    val name = "애크미 구스베리"
    val country = "GB"
}

class Organization02(
    val name: String,
    val country: String
) {
    val greetings: String
        get() = "Hello, I'm $name live in $country!"
}
