package ch01

import ch01.data.Invoice
import ch01.data.Performance
import ch01.data.Play
import ch01.data.PlayType
import ch01.sec01.statement
import ch01.sec04.Statement
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Ch01Test {
    companion object {
        private lateinit var invoice: Invoice
        private lateinit var plays: Map<String, Play>

        @JvmStatic
        @BeforeAll
        fun setUp(): Unit {
            invoice = Invoice(
                "BigCo",
                listOf(
                    Performance("hamlet", 55),
                    Performance("as-like", 35),
                    Performance("othello", 40)
                )
            )

            plays = mapOf(
                "hamlet" to Play("Hamlet", PlayType.TRAGEDY),
                "as-like" to Play("As You Like It", PlayType.COMEDY),
                "othello" to Play("Othello", PlayType.TRAGEDY)
            )
        }
    }

    @Test
    fun `Ch01-Code01 테스트`() {
        val expected: String = """
            청구 내역 (고객명: BigCo)
              Hamlet: ${'$'}650.00 (55석)
              As You Like It: ${'$'}580.00 (35석)
              Othello: ${'$'}500.00 (40석)
            총액: ${'$'}1,730.00
            적립 포인트: 47점
        """.trimIndent()

        val actual = statement(invoice, plays)

        assertEquals(expected, actual)
    }

    @Test
    fun `Ch01-Code02 테스트`() {
        val expected: String = """
            청구 내역 (고객명: BigCo)
              Hamlet: ${'$'}650.00 (55석)
              As You Like It: ${'$'}580.00 (35석)
              Othello: ${'$'}500.00 (40석)
            총액: ${'$'}1,730.00
            적립 포인트: 47점
        """.trimIndent()

        val statement = Statement(invoice, plays)
        val actual = statement.statement()

        assertEquals(expected, actual)
    }
}
