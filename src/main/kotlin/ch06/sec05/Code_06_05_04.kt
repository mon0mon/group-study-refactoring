package ch06.sec05

import ch06.data.Customer

/**
 * 6.6 변수 캡슐화하기
 * 예시 : 매개변수를 속성으로 바꾸기
 */
private fun inNewEngland_Old(customer: Customer): Boolean {
    return listOf("MA", "CT", "ME", "VT", "NH", "RI").contains(customer.address.state)
}

private fun inNewEngland_Refactored(stateCode: String): Boolean {
    return listOf("MA", "CT", "ME", "VT", "NH", "RI").contains(stateCode)
}
