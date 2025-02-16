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
    return countOrders(commandLine = CommandLine.parse(args))
}

private fun countOrders(commandLine: CommandLine): Int {
    val input = Paths.get(commandLine.filename).toFile()

    val mapper = ObjectMapper()
    val orders = mapper.readValue(input, Array<Order>::class.java)

    if (commandLine.onlyCountReady)
        return orders.filter { o -> "ready".equals(o.status) }.count()
    else
        return orders.size
}

class CommandLine(
    val filename: String,
    val onlyCountReady: Boolean = true
) {
    companion object {
        fun parse(args: Array<String>): CommandLine {
            require (args.isNotEmpty()) { throw RuntimeException("파일명을 입력하세요.") }

            return CommandLine(
                filename = args[args.size - 1],
                onlyCountReady = Stream.of(args).anyMatch { arg -> "-r".equals(arg) }
            )
        }
    }
}
