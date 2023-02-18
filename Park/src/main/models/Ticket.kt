package main.models

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class Ticket(val ticketNumber: Long, val spotNumber: Long, val entryDateTime: LocalDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)) {
    fun getTicketEntryDateTime():LocalDateTime{
        return entryDateTime
    }


    fun getTicketSpotNumber():Long{
        return spotNumber
    }

    fun getSpotNumberForTicket(): Long {
        return spotNumber
    }
}
