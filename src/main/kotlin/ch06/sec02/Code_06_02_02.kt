package ch06.sec02

import ch06.data.Customer

private fun reportLines_Old(aCustomer: Customer): Map<String, String> {
    val lines: MutableMap<String, String> = mutableMapOf()
    gatherCustomerData(lines, aCustomer)
    return lines
}

private fun reportLines_Refactored(aCustomer: Customer): Map<String, String> {
    val lines: MutableMap<String, String> = mutableMapOf()
    lines["name"] = aCustomer.name
    lines["location"] = aCustomer.location
    return lines
}

private fun gatherCustomerData(lines: MutableMap<String, String>, aCustomer: Customer) {
    lines["name"] = aCustomer.name
    lines["location"] = aCustomer.location
}
