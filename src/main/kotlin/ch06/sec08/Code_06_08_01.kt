package ch06.sec08

import ch06.data.DateRange
import java.time.LocalDate

class UnnamedClass_Old {
    private fun amountInvoiced(startDate: LocalDate, endDate: LocalDate) {}
    private fun amountReceived(startDate: LocalDate, endDate: LocalDate) {}
    private fun amountOverdue(startDate: LocalDate, endDate: LocalDate) {}
}

class UnnamedClass_Refactored {
    private fun amountInvoiced(aDateRange: DateRange) {}
    private fun amountReceived(aDateRange: DateRange) {}
    private fun amountOverdue(aDateRange: DateRange) {}
}
