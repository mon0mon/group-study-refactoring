package ch01.sec06

import ch01.data.*
import ch01.sec08.Statement
import java.text.NumberFormat
import java.util.*
import kotlin.math.floor

class Statement(
    private val invoice: Invoice,
    private val plays: Map<String, Play>
) {
    // statement.js
    fun statement(): String {
        val statementData = createStatementData()
        return renderPlainText(statementData)
    }

    fun htmlStatement(): String {
        val statementData = createStatementData()
        return renderHtml(statementData)
    }

    private fun createStatementData(): StatementData {
        return StatementData(
            customer = invoice.customer, enrichPerformances = enrichPerformance(invoice.performances),
            totalAmount = totalAmount(invoice.performances),
            totalVolumeCredits = totalVolumeCredits(invoice.performances)
        )
    }

    private fun renderPlainText(data: StatementData): String {
        var result = "청구 내역 (고객명: ${data.customer})\n"

        for (perf in data.enrichPerformances) {
            result += "  ${perf.play.name}: ${usd(amountFor(perf.performance))} (${perf.performance.audience}석)\n"
        }

        result += "총액: ${usd(data.totalAmount)}\n"
        result += "적립 포인트: ${data.totalVolumeCredits}점\n"

        return result.trimIndent()
    }

    private fun renderHtml(data: StatementData): String {
        var result = "<h1>청구 내역 (고객명: ${data.customer})</h1>\n"
        result += "<table>\n"
        result += """
            <tr>
                <th>연극</th>
                <th>좌석수</th>
                <th>금액</th>
            </tr>
            
        """.trimIndent()

        for (perf in data.enrichPerformances) {
            result += """
            <tr>
                <td>
                    ${perf.play.name}
                </td>
                <td>
                    ${perf.performance.audience}
                </td>
                <td>
                    ${usd(amountFor(perf.performance))}
                </td>
            </tr>
            
            """.trimIndent()
        }

        result += "</table>\n"
        result += "<p>총액: <em>${usd(data.totalAmount)}</em></p>\n"
        result += "<p>적립 포인트: <em>${data.totalVolumeCredits}</em>점</p>"

        return result
    }

    private fun enrichPerformance(aPerformances: List<Performance>): List<EnrichPerformance> {
        val result = mutableListOf<EnrichPerformance>()

        for (perf in aPerformances) {
            result.add(
                EnrichPerformance(
                    performance = perf.copy(), play = playFor(perf), amount = amountFor(perf),
                    volumeCredits = volumeCreditsFor(perf)
                )
            )
        }

        return result
    }

    private fun usd(number: Int): String? {
        return NumberFormat.getCurrencyInstance(Locale.US).format(number / 100.0)
    }

    // createStatementData.js
    private fun playFor(performance: Performance): Play {
        return plays[performance.playID] ?: throw IllegalArgumentException()
    }

    private fun amountFor(aPerformance: Performance): Int {
        var result: Int = 0

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

    private fun volumeCreditsFor(aPerformance: Performance): Int {
        var result = 0
        result += (aPerformance.audience - 30).coerceAtLeast(0)

        if (playFor(aPerformance).type == PlayType.COMEDY) {
            result += floor(aPerformance.audience / 5.0).toInt()
        }

        return result
    }

    private fun totalVolumeCredits(performances: List<Performance>): Int {
        var result = 0

        for (perf in performances) {
            result += volumeCreditsFor(perf)
        }

        return result
    }

    private fun totalAmount(aPerformances: List<Performance>): Int {
        var result = 0

        for (perf in aPerformances) {
            result += amountFor(perf)
        }

        return result
    }
}

fun main() {
    val invoice = Invoice(
        "BigCo",
        listOf(
            Performance("hamlet", 55), Performance("as-like", 35),
            Performance("othello", 40)
        )
    )

    val plays = mapOf(
        "hamlet" to Play("Hamlet", PlayType.TRAGEDY), "as-like" to Play("As You Like It", PlayType.COMEDY),
        "othello" to Play("Othello", PlayType.TRAGEDY)
    )

    val statement = Statement(invoice, plays)
    val renderStrings = statement.statement()

    println("PlainText:")
    println(renderStrings[0])

    println("\n\nHTML:")
    println(renderStrings[1])
}
