package models.locations

import exceptions.FloorDoesNotExistException
import exceptions.InvalidTicketException
import exceptions.TicketDoesNotExistException
import models.FloorTracker
import models.ReceiptBooth
import models.TicketBooth
import models.VehicleType.CAR
import models.feemodels.FeeModel
import models.feecalculators.CarAirportFeeCalculator
import models.feecalculators.FeeCalculator
import models.receipts.NormalReceipt
import models.tickets.NormalTicket
import models.vehicles.Vehicle
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Duration
import java.time.LocalDateTime

class BuildingTest {
    private lateinit var feeCalculator: FeeCalculator
    private lateinit var receiptBooth: ReceiptBooth
    private lateinit var ticketBooth: TicketBooth
    private lateinit var floorTracker: FloorTracker

    @BeforeEach
    fun setUp() {
        feeCalculator = CarAirportFeeCalculator()
        receiptBooth = ReceiptBooth(feeCalculator)
        ticketBooth = TicketBooth()
    }

    @DisplayName("should park a vehicle in the floor created")
    @Test
    fun parkVehicleInFirstFloor() {
        floorTracker = FloorTracker(listOf(mapOf(CAR to 10)))
        val car = Vehicle(CAR)
        val building = Location(ticketBooth, receiptBooth, floorTracker)
        val entryTime = LocalDateTime.now()
        val expectedTicket = NormalTicket(1L, 1, 1, entryTime)

        val actualTicket = building.parkVehicle(car, entryTime)

        assertEquals(expectedTicket, actualTicket)
    }

    @DisplayName("should park a vehicle in the available floor")
    @Test
    fun parkVehicleInAvailableFloor() {
        floorTracker = FloorTracker(listOf(mapOf(CAR to 1), mapOf(CAR to 1)))
        val car1 = Vehicle(CAR)
        val car2 = Vehicle(CAR)
        val building = Location(ticketBooth, receiptBooth, floorTracker)
        val entryTime = LocalDateTime.now()
        building.parkVehicle(car1, entryTime)
        val expectedTicket = NormalTicket(2L, 2, 1, entryTime)

        val actualTicket = building.parkVehicle(car2, entryTime)

        assertEquals(expectedTicket, actualTicket)
    }

    @DisplayName("should unpark a vehicle in the 1st floor")
    @Test
    fun unparkInFirstFloor() {
        floorTracker = FloorTracker(listOf(mapOf(CAR to 1)))
        val car1 = Vehicle(CAR)
        val building = Location(ticketBooth, receiptBooth, floorTracker)
        val entryTime = LocalDateTime.now()
        building.parkVehicle(car1, entryTime)
        val exitTime = LocalDateTime.now()
        val duration = Duration.between(entryTime, exitTime).toHours()
        val fee = feeCalculator.getFinalPrice(duration)
        val expectedReceipt = NormalReceipt(1L, 1, 1, entryTime, fee, exitTime)

        val actualReceipt = building.unparkVehicle(car1, exitTime)

        assertEquals(expectedReceipt, actualReceipt)
    }

    @DisplayName("should give the 1st unparked vehicles spot to a new vehicle")
    @Test
    fun reuseSpotForPark() {
        floorTracker = FloorTracker(listOf(mapOf(CAR to 1)))
        val car1 = Vehicle(CAR)
        val car2 = Vehicle(CAR)
        val entryTime1 = LocalDateTime.now()
        val building = Location(ticketBooth, receiptBooth, floorTracker)
        building.parkVehicle(car1, entryTime1)
        building.unparkVehicle(car1, LocalDateTime.now())
        val entryTime2 = LocalDateTime.now()
        val expectedTicket = NormalTicket(2L, 1, 1, entryTime2)

        val actualTicket = building.parkVehicle(car2, entryTime2)

        assertEquals(expectedTicket, actualTicket)
    }

    @DisplayName("should park a vehicle for multiple hours")
    @Test
    fun parkForMultipleHours() {
        val car1 = Vehicle(CAR)
        floorTracker = FloorTracker(listOf(mapOf(CAR to 1)))
        val entryTime = LocalDateTime.now().minusDays(2)
        val exitTime = LocalDateTime.now()
        val duration = Duration.between(entryTime, exitTime).toHours()
        val building = Location(ticketBooth, receiptBooth, floorTracker)
        building.parkVehicle(car1, entryTime)
        val expectedReceipt = NormalReceipt(
            1L,
            1,
            1,
            entryTime,
            feeCalculator.getFinalPrice(duration),
            exitTime
        )

        val actualReceipt = building.unparkVehicle(car1, exitTime)

        assertEquals(expectedReceipt, actualReceipt)
    }

    @DisplayName("should get a null ticket")
    @Test
    fun getANullTicket() {
        val car1 = Vehicle(CAR)
        floorTracker = FloorTracker(listOf(mapOf(CAR to 0)))
        val entryTime = LocalDateTime.now()
        val building = Location(ticketBooth, receiptBooth, floorTracker)

        val actualTicket = building.parkVehicle(car1, entryTime)

        assertEquals(true, actualTicket.isNull())
    }

    @DisplayName("should throw an invalid ticket exception")
    @Test
    fun getInvalidTicket() {
        val car = Vehicle(CAR)
        floorTracker = FloorTracker(listOf( mapOf(CAR to -1)))
        val building = Location(ticketBooth, receiptBooth, floorTracker)
        car.setTicketTo(NormalTicket(1L, -1, -1, LocalDateTime.now()))

        val expectedErrorMessage = "Invalid ticket"
        assertThrows<InvalidTicketException>(expectedErrorMessage) { building.unparkVehicle(car, LocalDateTime.now()) }
    }

    @DisplayName("should throw an invalid floor exception")
    @Test
    fun getInvalidFloor() {
        val car = Vehicle(CAR)
        floorTracker = FloorTracker(listOf(mapOf(CAR to 3)))
        val entryTime = LocalDateTime.now()
        val building = Location(ticketBooth, receiptBooth, floorTracker)
        building.parkVehicle(car, entryTime)
        car.setTicketTo(NormalTicket(1L, 2, 1, entryTime))

        val expectedErrorMessage = "Floor does not exist"
        assertThrows<FloorDoesNotExistException>(expectedErrorMessage) {
            building.unparkVehicle(
                car,
                LocalDateTime.now()
            )
        }
    }

    @DisplayName("should throw ticket does not exist exception")
    @Test
    fun getTicketDoesNotExist() {
        val car = Vehicle(CAR)
        floorTracker = FloorTracker(listOf(mapOf(CAR to 3)))
        val building = Location(ticketBooth, receiptBooth, floorTracker)
        val exitTime = LocalDateTime.now()

        val expectedErrorMessage = "Ticket does not exist"
        assertThrows<TicketDoesNotExistException>(expectedErrorMessage) { building.unparkVehicle(car, exitTime) }
    }

}