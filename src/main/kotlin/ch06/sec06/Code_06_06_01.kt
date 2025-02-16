package ch06.sec06

import ch06.data.Person

private var defaultOwner = Person(firstName = "마틴", lastName = "파울러")

class unnamedClass {
    var defaultOwner: Person = Person(firstName = "마틴", lastName = "파울러")
        get() {
            // 추가 로직 삽입

            return field
        }
        set(value) {
            // 추가 로직 삽입

            field = value
        }
}
