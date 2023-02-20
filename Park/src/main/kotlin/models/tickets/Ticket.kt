package models.tickets

import java.time.LocalDateTime
import kotlin.math.floor

interface Ticket{
    fun getTicketEntryDateTime(): LocalDateTime

    fun getSpotNumberForTicket(): Long

    fun getFloorNumberForTicket(): Long

    fun isNull(): Boolean
}
