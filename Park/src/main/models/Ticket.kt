package main.models

import java.time.LocalDateTime

data class Ticket(val ticketNumber: Long, val spotNumber: Long, val entryDateTime: LocalDateTime = LocalDateTime.now()) {
    fun getTicketEntryDateTime():LocalDateTime{
        return entryDateTime
    }

    fun getNumberForTicket(): Long {
        return ticketNumber
    }
}
