package main.models

import java.time.LocalDateTime

data class Ticket(
    private val ticketNumber: Long,
    private val spotNumber: Long,
    private val entryDateTime: LocalDateTime,
    private val floorNumber: Long = 1L
) {
    fun getTicketEntryDateTime(): LocalDateTime {
        return entryDateTime
    }

    fun getSpotNumberForTicket(): Long {
        return spotNumber
    }
}
