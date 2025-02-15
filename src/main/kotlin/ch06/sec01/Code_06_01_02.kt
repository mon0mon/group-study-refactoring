package ch06.sec01

import ch06.data.Invoice
import java.time.LocalDate

/**
 * 6.1 함수 추출하기
 * 예시: 유효범위를 벗어나는 변수가 없을 때
 */

private fun printOwing_Old(invoice: Invoice) {
    var outstanding = 0

    println("********")
    println("**** 고객 채무 ****")
    println("********")

    //  미해결 채무(outstanding)를 계산한다.
    outstanding = invoice.orders.sumOf { it.amount }

    //  마감일(dueDate)를 기록한다
    val today = LocalDate.now()
    invoice.dueDate = today.plusDays(30)

    //  세부 사항을 출력
    println("고객명 : ${invoice.customer}")
    println("채무액 : $outstanding")
    println("마감일 : ${invoice.dueDate}")
}

private fun printOwing_Refacored_V1(invoice: Invoice) {
    var outstanding = 0

    //  배너 출력 로직을 함수로 추출
    printBanner()

    //  미해결 채무(outstanding)를 계산한다.
    outstanding = invoice.orders.sumOf { it.amount }

    //  마감일(dueDate)를 기록한다
    val today = LocalDate.now()
    invoice.dueDate = today.plusDays(30)

    fun printDetails() {
        println("고객명 : ${invoice.customer}")
        println("채무액 : $outstanding")
        println("마감일 : ${invoice.dueDate}")
    }

    //  세부 사항을 출력을 함수로 추출
    printDetails()
}

private fun printBanner() {
    println("********")
    println("**** 고객 채무 ****")
    println("********")
}
