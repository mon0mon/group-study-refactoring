package ch04

import ch04.data.json
import ch04.data.provinceJson
import ch04.sec02.Province
import io.github.oshai.kotlinlogging.KotlinLogging
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private val logger = KotlinLogging.logger {  }

class Sec03Test {
    private lateinit var asia: Province

    @BeforeEach
    fun setUp() {
        asia = json.decodeFromString(Province.serializer(), provinceJson)
    }

    @Test
    fun `생산 부족분 테스트`() {
        logger.info { "province.shortFall = ${asia.shortFall}" }

        Assertions.assertThat(asia.shortFall).isEqualTo(5)
    }
}
