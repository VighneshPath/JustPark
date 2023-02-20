package models

import models.Ticket
import java.time.LocalDateTime

class TicketBooth {
    private var ticketCounter: Long = 0
    fun getTicket(floorNumber: Long, spotNumber: Long, entryTime: LocalDateTime): Ticket {
        ticketCounter++

        return Ticket(ticketCounter,
            floorNumber,
            spotNumber,
            entryTime
        )
    }
}