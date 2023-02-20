package models

import models.tickets.NormalTicket
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TicketBoothTest{
    private lateinit var ticketBooth: TicketBooth
    @BeforeEach
    fun resetTicketBooth(){
        ticketBooth = TicketBooth()
    }
    @DisplayName("should get a ticket for a spot and entry time")
    @Test
    fun getTicketForSpotAndEntry(){
        val entryTime = LocalDateTime.now()
        val spotNumber = 1L
        val expectedTicket = NormalTicket(1L, 1L, spotNumber, entryTime)

        val actualTicket = ticketBooth.getTicket(1L, spotNumber, entryTime)

        assertEquals(expectedTicket, actualTicket)
    }

}