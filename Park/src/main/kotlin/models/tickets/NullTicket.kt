package models.tickets

import java.time.LocalDateTime

class NullTicket: Ticket {
    override fun getTicketEntryDateTime(): LocalDateTime {
        return LocalDateTime.MIN
    }

    override fun getSpotNumberForTicket(): Long {
        return -1
    }

    override fun getFloorNumberForTicket(): Long{
        return -1
    }

    override fun isNull(): Boolean {
        return true
    }
}