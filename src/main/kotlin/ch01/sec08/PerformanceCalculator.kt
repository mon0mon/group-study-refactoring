package ch01.sec08

import ch01.data.Performance
import ch01.data.Play
import ch01.data.PlayType
import kotlin.math.floor

abstract class PerformanceCalculator(protected val performance: Performance, protected val play: Play) {

    companion object {
        fun createPerformanceCalculator(performance: Performance, play: Play): PerformanceCalculator {
            return when (play.type) {
                PlayType.TRAGEDY -> TragedyCalculator(performance, play)
                PlayType.COMEDY -> ComedyCalculator(performance, play)
                else -> throw IllegalArgumentException("알 수 없는 장르: ${play.type}")
            }
        }
    }

    abstract fun amount(): Int

    open fun volumeCredits(): Int {
        return (performance.audience - 30).coerceAtLeast(0)
    }
}

class TragedyCalculator(performance: Performance, play: Play) : PerformanceCalculator(performance, play) {

    override fun amount(): Int {
        var result: Int = 40000

        if (performance.audience > 30) {
            result += 1000 * (performance.audience - 30)
        }

        return result
    }
}

class ComedyCalculator(performance: Performance, play: Play) : PerformanceCalculator(performance, play) {

    override fun amount(): Int {
        var result = 30000

        if (performance.audience > 20) {
            result += 10000 + 500 * (performance.audience - 20)
        }

        return result + 300 * performance.audience
    }

    override fun volumeCredits(): Int {
        return super.volumeCredits() + floor(performance.audience / 5.0).toInt()
    }
}
