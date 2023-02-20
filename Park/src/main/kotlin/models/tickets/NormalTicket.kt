package models.tickets

import java.time.LocalDateTime

data class NormalTicket(
    private val ticketNumber: Long,
    private val floorNumber: Long,
    private val spotNumber: Long,
    private val entryDateTime: LocalDateTime
) : Ticket {
    override fun getTicketEntryDateTime(): LocalDateTime {
        return entryDateTime
    }

    override fun getSpotNumberForTicket(): Long {
        return spotNumber
    }

    override fun getFloorNumberForTicket(): Long {
        return floorNumber
    }

    override fun isNull(): Boolean {
        return false
    }
}