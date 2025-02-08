package ch04.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val provinceJson = """
{
  "name": "Asia",
  "producers": [
    {
      "name": "Byzantium",
      "cost": 10,
      "production": 9
    },
    {
      "name": "Attalia",
      "cost": 12,
      "production": 10
    },
    {
      "name": "Sinope",
      "cost": 10,
      "production": 6
    }
  ],
  "demand": 30,
  "price": 20
}
""".trimIndent()

@Serializable
data class Producer(
    val name: String,
    var cost: Int,
    var production: Int
)

val json = Json { ignoreUnknownKeys = true }

