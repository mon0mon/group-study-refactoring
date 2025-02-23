package ch07

import ch07.sec01.ClientInformation
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

private val logger = KotlinLogging.logger { }

class Sec01Test {

    private val jsonString = """
    {
      "1920": {
        "name": "마틴 파울러",
        "id": 1920,
        "usages": {
          "2016": {
            "1": 50,
            "2": 55,
            "3": 54,
            "4": 86,
            "5": 60,
            "6": 19,
            "7": 7,
            "8": 69,
            "9": 25,
            "10": 67,
            "11": 20,
            "12": 63
          },
          "2015": {
            "1": 70,
            "2": 63,
            "3": 46,
            "4": 50,
            "5": 60,
            "6": 55,
            "7": 60,
            "8": 54,
            "9": 58,
            "10": 23,
            "11": 54,
            "12": 30
          }
        }
      }
    }
    """.trimIndent()
    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun test() {
        val clientInformation = Json.decodeFromString<Map<String, ClientInformation>>(jsonString)

        logger.info { "clientInformation = $clientInformation" }
    }
}
