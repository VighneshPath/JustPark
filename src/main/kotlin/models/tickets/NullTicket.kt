package models.tickets

import java.time.LocalDateTime

class NullTicket : Ticket {
    override fun getTicketEntryDateTime(): LocalDateTime = LocalDateTime.MIN

    override fun getSpotNumberForTicket() = -1

    override fun getFloorNumberForTicket() = -1

    override fun isNull() = true
}