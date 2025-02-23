package ch07.sec09

data class UnnamedClass(
    val peoples: List<String> = listOf("Don", "John", "Kent")
) {

    fun foundPerson01(people: String): String {
        for (s in peoples) {
            if (s == "Don") {
                return "Don"
            }

            if (s == "John") {
                return "John"
            }

            if (s == "Kent") {
                return "Kent"
            }
        }

        return ""
    }

    fun foundPerson02(people: String): String {
        return peoples.firstOrNull() { it == people } ?: ""
    }
}
