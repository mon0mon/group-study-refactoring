package ch04

import ch04.data.json
import ch04.data.provinceJson
import ch04.sec02.Province
import io.github.oshai.kotlinlogging.KotlinLogging
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private val logger = KotlinLogging.logger { }

class Sec05Test {
    private lateinit var asia: Province

    @BeforeEach
    fun setUp() {
        asia = json.decodeFromString(Province.serializer(), provinceJson)
    }

    @Test
    fun `생산량 변경`() {
        asia.producers[0].production = 20

        logger.info { "province.shortfall = ${asia.shortFall}" }
        logger.info { "province.profit = ${asia.profit}" }

        Assertions.assertThat(asia.shortFall).isEqualTo(-6)
        Assertions.assertThat(asia.profit).isEqualTo(292)
    }
}
