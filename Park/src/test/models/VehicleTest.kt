package test.models

import main.models.Ticket
import main.models.Vehicle
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.assertj.core.api.Assertions.assertThat
import java.time.LocalDateTime


class VehicleTest{
    @DisplayName("should park a vehicle in a spot")
    @Test
    fun parkVehicleInASpot(){
        val car = Vehicle()
        val expectedParkingStatus = true

        car.park()

        assertEquals(expectedParkingStatus, car.isParked())
    }

    @DisplayName("should park a vehicle in a spot and get a ticket")
    @Test
    fun parkAndGetTicket(){
        val car = Vehicle()
        val expectedTicketNumber = 1L
        val expectedSpotNumber = 1L
        val expectedTicket = Ticket(expectedTicketNumber, expectedSpotNumber)

        val actualTicket = car.park()
        println(actualTicket)
        println(expectedTicket)

        assertThat(actualTicket).usingRecursiveComparison()
            .comparingOnlyFields("ticketNumber", "spotNumber")
            .isEqualTo(expectedTicket)
    }
}