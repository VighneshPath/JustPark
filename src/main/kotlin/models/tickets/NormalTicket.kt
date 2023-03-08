package models.tickets

import java.time.LocalDateTime

data class NormalTicket(
    private val ticketNumber: Long,
    private val floorNumber: Int,
    private val spotNumber: Int,
    private val entryDateTime: LocalDateTime
) : Ticket {
    override fun getTicketEntryDateTime(): LocalDateTime {
        return entryDateTime
    }

    override fun getSpotNumberForTicket(): Int {
        return spotNumber
    }

    override fun getFloorNumberForTicket(): Int {
        return floorNumber
    }

    override fun isNull(): Boolean {
        return false
    }
}