package models

import java.time.LocalDateTime

data class Ticket(
    private val ticketNumber: Long,
    private val spotNumber: Long,
    private val entryDateTime: LocalDateTime,
    private val floorNumber: Int = 1
) {
    fun getTicketEntryDateTime(): LocalDateTime {
        return entryDateTime
    }

    fun getSpotNumberForTicket(): Long {
        return spotNumber
    }

    fun getFloorNumberForTicket(): Int {
        return floorNumber
    }
}
