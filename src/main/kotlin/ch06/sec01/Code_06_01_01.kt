package ch06.sec01

import ch06.data.Invoice
import kotlin.random.Random

private fun printOwing_Old(invoice: Invoice) {
    printBanner()
    val outstanding = calculateOutstanding()

    //  세부 사항 출력
    println("고객명 : ${invoice.customer}")
    println("채무액: $outstanding")
}

private fun printOwing_Refactored(invoice: Invoice) {
    printBanner()
    val outstanding = calculateOutstanding()

    printDetails(invoice, outstanding)
}

private fun printBanner() {}

private fun printDetails(invoice: Invoice, outstanding: Int) {
    println("고객명 : ${invoice.customer}")
    println("채무액: $outstanding")
}

private fun calculateOutstanding() = Random(System.currentTimeMillis()).nextInt()
