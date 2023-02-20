package test.models

import main.models.Ticket
import main.models.vehicles.Car
import main.models.vehicles.Vehicle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class VehicleTest{
    private lateinit var vehicle : Vehicle
    @BeforeEach
    fun setUp(){
        vehicle = Car()
    }
    @DisplayName("should set ticket for a vehicle")
    @Test
    fun setVehicleTicket(){
        val entryTime = LocalDateTime.now()
        val ticket = Ticket(1L, 1L, entryTime)

        vehicle.setTicketTo(ticket)

        assertEquals(vehicle.getVehicleTicket(), ticket)
    }
}