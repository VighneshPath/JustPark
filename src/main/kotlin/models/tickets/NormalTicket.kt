package models.tickets

import java.time.LocalDateTime

data class NormalTicket(
    private val ticketNumber: Long,
    private val floorNumber: Int,
    private val spotNumber: Int,
    private val entryDateTime: LocalDateTime
) : Ticket {
    override fun getTicketEntryDateTime() = entryDateTime

    override fun getSpotNumberForTicket() = spotNumber

    override fun getFloorNumberForTicket() = floorNumber
    override fun isNull() = false
}