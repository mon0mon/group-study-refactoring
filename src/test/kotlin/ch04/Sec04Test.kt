package ch04

import ch04.data.json
import ch04.data.provinceJson
import ch04.sec02.Province
import io.github.oshai.kotlinlogging.KotlinLogging
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private val logger = KotlinLogging.logger { }

class Sec04Test {
    private lateinit var asia: Province

    @BeforeEach
    fun setUp() {
        asia = json.decodeFromString(Province.serializer(), provinceJson)
    }

    @Test
    fun `총수익 계산 로직`() {
        logger.info { "province.profit = ${asia.profit}" }

        Assertions.assertThat(asia.profit).isEqualTo(230)
    }
}
