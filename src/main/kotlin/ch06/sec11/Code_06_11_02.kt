package ch06.sec11

import ch06.data.Order
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import java.nio.file.Paths
import java.util.stream.Stream

private val logger = KotlinLogging.logger { }

private fun main(args: Array<String>) {
    try {
        if (args.size == 0) throw RuntimeException("파일명을 입력하세요.")

        var filename = args[args.size - 1]
        val input = Paths.get(filename).toFile()

        val mapper = ObjectMapper()
        val orders = mapper.readValue(input, Array<Order>::class.java)

        if (Stream.of(args).anyMatch { arg -> "-r".equals(arg) })
            logger.info { orders.filter { o -> "ready".equals(o.status) }.count() }
        else
            logger.info { orders.size }
    } catch (e: Exception) {
        logger.error { e }
    }
}

private fun main2(args: Array<String>) {
    try {
        logger.info { run(args) }
    } catch (e: Exception) {
        logger.error { e }
    }
}

private fun run(args: Array<String>): Int {
    return countOrders(commandLine = CommandLine2(args))
}

private fun countOrders(commandLine: CommandLine2): Int {
    val input = Paths.get(commandLine.filename).toFile()

    val mapper = ObjectMapper()
    val orders = mapper.readValue(input, Array<Order>::class.java)

    if (commandLine.onlyCountReady)
        return orders.filter { o -> "ready".equals(o.status) }.count()
    else
        return orders.size
}

class CommandLine2(
    args: Array<String>
) {
    val filename: String = args[args.size - 1]
    val onlyCountReady: Boolean = Stream.of(args).anyMatch { arg -> "-r".equals(arg) }

    init {
        require(args.isNotEmpty()) { "파일명을 입력하세요" }
    }
}
