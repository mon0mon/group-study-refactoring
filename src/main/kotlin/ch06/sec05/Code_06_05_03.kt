package ch06.sec05

import ch06.data.Customer

/**
 * 6.6 변수 캡슐화하기
 * 예시 : 매개변수 추가하기
 */

private data class Book(
    val reservations: MutableList<Customer>
) {
    private fun addReservation_Old(customer: Customer) {
        this.reservations.add(customer)
    }

    private fun addReservation_Refactored(customer: Customer, isPriority: Boolean) {
        require(isPriority || !isPriority)
        this.reservations.add(customer)
    }
}
