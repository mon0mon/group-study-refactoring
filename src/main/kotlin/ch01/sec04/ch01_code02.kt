package ch01.sec04

import ch01.data.Invoice
import ch01.data.Performance
import ch01.data.Play
import ch01.data.PlayType
import java.text.NumberFormat
import java.util.*
import kotlin.math.floor

class Statement(
    private val invoice: Invoice,
    private val plays: Map<String, Play>
) {
    private fun playFor(aPerformance: Performance): Play {
        return plays[aPerformance.playID] ?: throw IllegalArgumentException()
    }

    private fun amountFor(aPerformance: Performance): Int {
        var result = 0

        when (playFor(aPerformance).type) {
            PlayType.TRAGEDY -> {
                result = 40000
                if (aPerformance.audience > 30) {
                    result += 1000 * (aPerformance.audience - 30)
                }
            }

            PlayType.COMEDY -> {
                result = 30000
                if (aPerformance.audience > 20) {
                    result += 10000 + 500 * (aPerformance.audience - 20)
                }
                result += 300 * aPerformance.audience
            }

            else -> throw IllegalArgumentException("알 수 없는 장르: ${playFor(aPerformance).type}")
        }

        return result
    }

    private fun volumeCreditsFor(volumeCredits: Int, perf: Performance): Int {
        var result = volumeCredits
        result += (perf.audience - 30).coerceAtLeast(0)

        if (playFor(perf).type == PlayType.COMEDY) {
            result += floor(perf.audience / 5.0).toInt()
        }

        return result
    }

    private fun usd(number: Int): String? {
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        return format.format(number / 100.0)
    }

    private fun totalVolumeCredits(): Int {
        var result = 0
        for (perf in invoice.performances) {
            result = volumeCreditsFor(result, perf)
        }
        return result
    }

    private fun totalAmount(): Int {
        var result = 0
        for (perf in invoice.performances) {
            result += amountFor(perf)
        }
        return result
    }

    fun statement(): String {
        var totalAmount: Int = 0
        var result = "청구 내역 (고객명: ${invoice.customer})\n"

        for (perf in invoice.performances) {
            result += "  ${playFor(perf).name}: ${usd(amountFor(perf))} (${perf.audience}석)\n"
        }

        totalAmount = totalAmount()

        result += "총액: ${usd(totalAmount)}\n"
        result += "적립 포인트: ${totalVolumeCredits()}점\n"

        return result.trimIndent()
    }
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

    val statement = Statement(invoice, plays)
    println(statement.statement())
}
