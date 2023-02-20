package models

import models.tickets.NormalTicket
import models.vehicles.Car
import models.vehicles.Vehicle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class VehicleTest {
    private lateinit var vehicle: Vehicle

    @BeforeEach
    fun setUp() {
        vehicle = Car()
    }

    @DisplayName("should set ticket for a vehicle")
    @Test
    fun setVehicleTicket() {
        val entryTime = LocalDateTime.now()
        val ticket = NormalTicket(1L, 1L, 1L, entryTime)

        vehicle.setTicketTo(ticket)

        assertEquals(vehicle.getVehicleTicket(), ticket)
    }
}