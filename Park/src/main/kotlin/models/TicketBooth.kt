package models

import models.Ticket
import java.time.LocalDateTime

class TicketBooth {
    private var ticketCounter: Long = 0
    fun getTicket(spotNumber: Long, entryTime: LocalDateTime): Ticket {
        ticketCounter++

        return Ticket(ticketCounter,
            spotNumber,
            entryTime
        )
    }
}