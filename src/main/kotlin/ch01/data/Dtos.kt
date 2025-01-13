package ch01.data

data class Invoice(
    var customer: String,
    var performances: List<Performance>
)

data class Performance(
    var playID: String,
    var audience: Int
)

data class Play(
    var name: String,
    var type: PlayType
)

enum class PlayType {
    TRAGEDY,
    COMEDY
}

data class StatementData(
    var customer: String = "",
    var enrichPerformances: List<EnrichPerformance> = listOf(),
    var totalAmount: Int = 0,
    var totalVolumeCredits: Int = 0
)

data class EnrichPerformance(
    var performance: Performance,
    var play: Play,
    var amount: Int,
    var volumeCredits: Int
)
