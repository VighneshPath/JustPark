package test.models

import main.models.ParkingLot
import main.models.Receipt
import main.models.Ticket
import main.models.Vehicle
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import java.time.LocalDateTime


class VehicleTest{
    private lateinit var parkingLot: ParkingLot
    @DisplayName("should reset parking lot")
    @BeforeEach
    fun resetParkingLot(){
        parkingLot = ParkingLot()
    }

    @DisplayName("should park a vehicle in a spot")
    @Test
    fun parkVehicleInASpot(){
        val car = Vehicle()
        val expectedParkingStatus = true

        car.park(parkingLot)

        assertEquals(expectedParkingStatus, car.isParked())
    }

    @DisplayName("should park a vehicle in a spot and get a ticket")
    @Test
    fun parkAndGetTicket(){
        val car = Vehicle()
        val expectedTicketNumber = 1L
        val expectedSpotNumber = 1L
        val expectedTicket = Ticket(expectedTicketNumber, expectedSpotNumber)

        val actualTicket = car.park(parkingLot)

        assertThat(actualTicket).usingRecursiveComparison()
            .comparingOnlyFields("ticketNumber", "spotNumber")
            .isEqualTo(expectedTicket)
    }

    @DisplayName("should park multiple vehicle different spots and get tickets")
    @Test
    fun parkMultipleAndGetMultipleTicket(){
        val car = Vehicle()
        val car2 = Vehicle()
        val expectedTicketNumber1 = 1L
        val expectedSpotNumber1 = 1L
        val expectedTicket1 = Ticket(expectedTicketNumber1, expectedSpotNumber1)
        val expectedTicketNumber2 = 2L
        val expectedSpotNumber2 = 2L
        val expectedTicket2 = Ticket(expectedTicketNumber2, expectedSpotNumber2)

        val actualTicket1 = car.park(parkingLot)
        val actualTicket2 = car2.park(parkingLot)

        assertThat(actualTicket1).usingRecursiveComparison()
            .comparingOnlyFields("ticketNumber", "spotNumber")
            .isEqualTo(expectedTicket1)
        assertThat(actualTicket2).usingRecursiveComparison()
            .comparingOnlyFields("ticketNumber", "spotNumber")
            .isEqualTo(expectedTicket2)
    }

    @DisplayName("should unpark vehicle")
    @Test
    fun unparkVehicle(){
        val car = Vehicle()
        val expectedParkingStatus = false
        car.park(parkingLot)

        car.unpark()

        assertEquals(expectedParkingStatus, car.isParked())
    }

    @DisplayName("should unpark vehicle and get a receipt")
    @Test
    fun unparkAndGetReceipt(){
        val car = Vehicle()
        val ticket = car.park(parkingLot)
        val expectedReceipt = Receipt(1L, 1L, ticket!!.getTicketEntryDateTime())

        val actualReceipt = car.unpark()

        assertThat(actualReceipt).usingRecursiveComparison()
            .comparingOnlyFields("receiptNumber", "spotNumber")
            .isEqualTo(expectedReceipt)
    }

}