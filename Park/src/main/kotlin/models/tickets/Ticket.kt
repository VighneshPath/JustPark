package models.tickets

import java.time.LocalDateTime

interface Ticket {
    fun getTicketEntryDateTime(): LocalDateTime

    fun getSpotNumberForTicket(): Long

    fun getFloorNumberForTicket(): Long

    fun isNull(): Boolean
}
