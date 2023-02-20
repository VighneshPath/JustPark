package models

import java.time.LocalDateTime
import kotlin.math.floor

data class Ticket(
    private val ticketNumber: Long,
    private val floorNumber: Long,
    private val spotNumber: Long,
    private val entryDateTime: LocalDateTime
) {
    fun getTicketEntryDateTime(): LocalDateTime {
        return entryDateTime
    }

    fun getSpotNumberForTicket(): Long {
        return spotNumber
    }

    fun getFloorNumberForTicket(): Long{
        return floorNumber
    }
}
