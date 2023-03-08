package models.tickets

import java.time.LocalDateTime

interface Ticket {
    fun getTicketEntryDateTime(): LocalDateTime

    fun getSpotNumberForTicket(): Int

    fun getFloorNumberForTicket(): Int

    fun isNull(): Boolean
}
