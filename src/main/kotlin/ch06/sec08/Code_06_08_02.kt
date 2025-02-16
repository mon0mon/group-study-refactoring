package ch06.sec08

import ch04.data.json
import ch06.data.NumberRange
import ch06.data.Station
import ch06.data.TemperatureData

private const val stationJson:String = """
    {
        name: "ZB1",
        readings: [
            {temp: 47, time: "2025-02-16 11:20"},
            {temp: 53, time: "2025-02-16 11:30"},
            {temp: 58, time: "2025-02-16 11:40"},
            {temp: 53, time: "2025-02-16 11:50"},
            {temp: 51, time: "2025-02-16 12:00"},
        ]
    }
"""

private val station = json.decodeFromString(Station.serializer(), stationJson)

private fun readingsOutsideRange_Old(station: Station, min: Int, max: Int): List<TemperatureData> {
    return station.readings.filter { r -> r.temp < min || r.temp > max }
}

private fun readingsOutsideRange_Refactored(station: Station, numberRange: NumberRange): List<TemperatureData> {
    return station.readings.filter { r -> r.temp < numberRange.min || r.temp > numberRange.max }
}

private fun readingsOutsideRange_Refactored_V2(station: Station, numberRange: NumberRange): List<TemperatureData> {
    return station.readings.filter { r -> !numberRange.contains(r.temp) }
}
