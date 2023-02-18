package main.models

import java.time.LocalDateTime

data class Ticket(private val ticketNumber: Long, private val spotNumber: Long, private val entryDateTime: LocalDateTime) {
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
