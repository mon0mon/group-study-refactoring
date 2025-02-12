package ch04

import ch04.data.json
import ch04.data.provinceJson
import ch04.sec02.Province
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.SerializationException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private val logger = KotlinLogging.logger { }

class Sec06Test {
    private lateinit var noProducer: Province
    private lateinit var asia: Province

    @BeforeEach
    fun setUp() {
        noProducer = Province(name = "No producers", producers = mutableListOf(), demand = 30, price = 20)
        asia = json.decodeFromString(Province.serializer(), provinceJson)
    }

    @Test
    fun `생산량 없는 경우`() {
        logger.info { "province.shortfall = ${noProducer.shortFall}" }
        logger.info { "province.profit = ${noProducer.profit}" }

        Assertions.assertThat(noProducer.shortFall).isEqualTo(30)
        Assertions.assertThat(noProducer.profit).isEqualTo(0)
    }

    @Test
    fun `수요가 0일 때`() {
        asia.demand = 0

        logger.info { "asia.demand = ${asia.demand}" }
        logger.info { "asia.shortfall = ${asia.shortFall}" }
        logger.info { "asia.profit = ${asia.profit}" }

        Assertions.assertThat(asia.demand).isEqualTo(0)
        Assertions.assertThat(asia.shortFall).isEqualTo(-25)
        Assertions.assertThat(asia.profit).isEqualTo(0)
    }

    @Test
    fun `수요가 음수 일 때`() {
        asia.demand = -1

        logger.info { "asia.demand = ${asia.demand}" }
        logger.info { "asia.shortfall = ${asia.shortFall}" }
        logger.info { "asia.profit = ${asia.profit}" }

        Assertions.assertThat(asia.demand).isEqualTo(-1)
        Assertions.assertThat(asia.shortFall).isEqualTo(-26)
        Assertions.assertThat(asia.profit).isEqualTo(-10)
    }

    @Test
    fun `수요가 비어있을 때`() {
        asia.demand = 0

        logger.info { "asia.demand = ${asia.demand}" }
        logger.info { "asia.shortfall = ${asia.shortFall}" }
        logger.info { "asia.profit = ${asia.profit}" }

        Assertions.assertThat(asia.demand).isEqualTo(0)
        Assertions.assertThat(asia.shortFall).isEqualTo(-25)
        Assertions.assertThat(asia.profit).isEqualTo(0)
    }

    @Test
    fun `생산자 목록 필드에 문자열 대입`() {
        val givenJson = """
            {
              "name": "Asia",
              "producers": "String",
              "demand": 30,
              "price": 20
            }
        """.trimIndent()

        val excep = Assertions.assertThatThrownBy { json.decodeFromString(Province.serializer(), givenJson) }
            .isInstanceOf(SerializationException::class.java)

        logger.info { "excep.message = ${excep.message().actual()}" }
    }
}
