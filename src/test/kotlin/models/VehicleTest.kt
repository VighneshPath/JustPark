package models

import models.VehicleType.CAR
import models.tickets.NormalTicket
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class VehicleTest {
    private var vehicle = Vehicle(CAR)

    @BeforeEach
    fun setUp() {
        vehicle = Vehicle(CAR)
    }

    @Test
    fun `should set ticket for a vehicle`(){
        val entryTime = LocalDateTime.now()
        val ticket = NormalTicket(1L, 1, 1, entryTime)

        vehicle.setTicketTo(ticket)

        assertEquals(vehicle.getVehicleTicket(), ticket)
    }
}