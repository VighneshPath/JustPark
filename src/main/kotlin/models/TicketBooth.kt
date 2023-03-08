package models

import models.tickets.NormalTicket
import models.tickets.Ticket
import java.time.LocalDateTime

class TicketBooth {
    private var ticketCounter: Long = 0
    fun getTicket(floorNumber: Int, spotNumber: Int, entryTime: LocalDateTime): Ticket {
        ticketCounter++

        return NormalTicket(
            ticketCounter,
            floorNumber,
            spotNumber,
            entryTime
        )
    }
}