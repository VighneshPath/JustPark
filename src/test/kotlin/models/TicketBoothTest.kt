package models

import models.tickets.NormalTicket
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TicketBoothTest {
    private var ticketBooth = TicketBooth()

    @BeforeEach
    fun resetTicketBooth() {
        ticketBooth = TicketBooth()
    }

    @DisplayName("should get a ticket for a spot and entry time")
    @Test
    fun getTicketForSpotAndEntry() {
        val entryTime = LocalDateTime.now()
        val spotNumber = 1
        val expectedTicket = NormalTicket(1L, 1, spotNumber, entryTime)

        val actualTicket = ticketBooth.getTicket(1, spotNumber, entryTime)

        assertEquals(expectedTicket, actualTicket)
    }

}