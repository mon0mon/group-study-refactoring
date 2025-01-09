package ch01.sec01

import ch01.data.Invoice
import ch01.data.Performance
import ch01.data.Play
import ch01.data.PlayType
import java.text.NumberFormat
import java.util.*
import kotlin.math.floor

fun statement(invoice: Invoice, plays: Map<String, Play>): String {
    var totalAmount: Int = 0
    var volumeCredits: Int = 0

    var result = "청구 내역 (고객명: ${invoice.customer})\n"
    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)

    for (perf in invoice.performances) {
        val play = plays[perf.playID]!!
        var thisAmount = 0

        when (play.type) {
            PlayType.TRAGEDY -> {
                thisAmount = 40000
                if (perf.audience > 30) {
                    thisAmount += 1000 * (perf.audience - 30)
                }
            }

            PlayType.COMEDY -> {
                thisAmount = 30000
                if (perf.audience > 20) {
                    thisAmount += 10000 + 500 * (perf.audience - 20)
                }
                thisAmount += 300 * perf.audience
            }

            else -> throw IllegalArgumentException("알 수 없는 장르: ${play.type}")
        }

        volumeCredits += (perf.audience - 30).coerceAtLeast(0)

        if (play.type == PlayType.COMEDY) {
            volumeCredits += floor(perf.audience / 5.0).toInt()
        }

        result += "  ${play.name}: ${format.format(thisAmount / 100.0)} (${perf.audience}석)\n"
        totalAmount += thisAmount
    }

    result += "총액: ${format.format(totalAmount / 100.0)}\n"
    result += "적립 포인트: ${volumeCredits}점\n"

    return result.trimIndent()
}

fun main() {
    val invoice = Invoice(
        "BigCo",
        listOf(
            Performance("hamlet", 55),
            Performance("as-like", 35),
            Performance("othello", 40)
        )
    )

    val plays = mapOf(
        "hamlet" to Play("Hamlet", PlayType.TRAGEDY),
        "as-like" to Play("As You Like It", PlayType.COMEDY),
        "othello" to Play("Othello", PlayType.TRAGEDY)
    )

    println(statement(invoice, plays))
}
