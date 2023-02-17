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
        val expectedSpotNumber = 0L
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
        val expectedSpotNumber1 = 0L
        val expectedTicket1 = Ticket(expectedTicketNumber1, expectedSpotNumber1)
        val expectedTicketNumber2 = 2L
        val expectedSpotNumber2 = 1L
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

    @DisplayName("should unpark vehicle and get a receipt with fee")
    @Test
    fun unparkAndGetReceipt(){
        val car = Vehicle()
        val ticket = car.park(parkingLot)
        val expectedReceipt = Receipt(1L, 0L, ticket!!.getTicketEntryDateTime(), 0L)

        val actualReceipt = car.unpark()

        assertThat(actualReceipt).usingRecursiveComparison()
            .comparingOnlyFields("receiptNumber", "spotNumber")
            .isEqualTo(expectedReceipt)
    }

    @DisplayName("should park and unpark multiple vehicle different spots and get receipts")
    @Test
    fun parkAndUnparkMultipleAndGetMultipleReceipt(){
        val car = Vehicle()
        val car2 = Vehicle()
        val ticket1 = car.park(parkingLot)
        val ticket2 = car2.park(parkingLot)
        val expectedReceiptNumber1 = 1L
        val expectedSpotNumber1 = 0L
        val expectedReceipt1 = Receipt(expectedReceiptNumber1, expectedSpotNumber1, ticket1!!.getTicketEntryDateTime(), 0L)
        val expectedReceiptNumber2 = 2L
        val expectedSpotNumber2 = 1L
        val expectedReceipt2 = Receipt(expectedReceiptNumber2, expectedSpotNumber2, ticket2!!.getTicketEntryDateTime(), 0L)

        val actualReceipt1 = car.unpark()
        val actualReceipt2 = car2.unpark()

        assertThat(actualReceipt1).usingRecursiveComparison()
            .comparingOnlyFields("receiptNumber", "spotNumber")
            .isEqualTo(expectedReceipt1)
        assertThat(actualReceipt2).usingRecursiveComparison()
            .comparingOnlyFields("receiptNumber", "spotNumber")
            .isEqualTo(expectedReceipt2)
    }

    @DisplayName("should park and unpark vehicles to get first free spot")
    @Test
    fun parkAndUnparkMultipleAndGetTicketWithSameSpot(){
        val car = Vehicle()
        car.park(parkingLot)
        val car2 = Vehicle()
        car2.park(parkingLot)
        car.unpark()
        val car3 = Vehicle()
        val expectedTicket = Ticket(3L, 0L)

        val actualTicket = car3.park(parkingLot)

        assertThat(actualTicket).usingRecursiveComparison()
            .comparingOnlyFields("ticketNumber", "spotNumber")
            .isEqualTo(expectedTicket)
    }

}