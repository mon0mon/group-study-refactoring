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
