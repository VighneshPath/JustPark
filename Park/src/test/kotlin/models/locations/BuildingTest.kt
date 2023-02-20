package models.locations

import exceptions.FloorDoesNotExistException
import exceptions.InvalidTicketException
import exceptions.TicketDoesNotExistException
import models.ReceiptBooth
import models.tickets.NormalTicket
import models.TicketBooth
import models.feecalculators.FeeCalculator
import models.feecalculators.HourlyFeeCalculator
import models.feemodels.CarForParkingLotFeeModel
import models.feemodels.FeeModel
import models.receipts.NormalReceipt
import models.vehicles.Car
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Duration
import java.time.LocalDateTime

class BuildingTest{
    private lateinit var feeCalculator: FeeCalculator
    private lateinit var feeModel: FeeModel
    private lateinit var receiptBooth: ReceiptBooth
    private lateinit var ticketBooth: TicketBooth
    @BeforeEach
    fun setUp(){
        feeCalculator = HourlyFeeCalculator()
        feeModel = CarForParkingLotFeeModel()
        receiptBooth = ReceiptBooth(feeCalculator, feeModel)
        ticketBooth = TicketBooth()
    }

    @DisplayName("should park a vehicle in the floor created")
    @Test
    fun parkVehicleInFirstFloor(){
        val floorsWithSize = listOf(10L)
        val car = Car()
        val building = Building(ticketBooth, receiptBooth, floorsWithSize)
        val entryTime = LocalDateTime.now()
        val expectedTicket = NormalTicket(1L, 1L, 1L, entryTime)

        val actualTicket = building.parkVehicle(car, entryTime)

        assertEquals(expectedTicket, actualTicket)
    }

    @DisplayName("should park a vehicle in the available floor")
    @Test
    fun parkVehicleInAvailableFloor(){
        val floorsWithSize = listOf(1L, 1L)
        val car1 = Car()
        val car2 = Car()
        val building = Building(ticketBooth, receiptBooth, floorsWithSize)
        val entryTime = LocalDateTime.now()
        building.parkVehicle(car1, entryTime)
        val expectedTicket = NormalTicket(2L, 2L, 1L, entryTime)

        val actualTicket = building.parkVehicle(car2, entryTime)

        assertEquals(expectedTicket, actualTicket)
    }

    @DisplayName("should unpark a vehicle in the 1st floor")
    @Test
    fun unparkInFirstFloor(){
        val floorsWithSize = listOf(1L)
        val car1 = Car()
        val building = Building(ticketBooth, receiptBooth, floorsWithSize)
        val entryTime = LocalDateTime.now()
        building.parkVehicle(car1, entryTime)
        val exitTime = LocalDateTime.now()
        val rate = feeModel.getRate()
        val duration = Duration.between(entryTime, exitTime).toHours()
        val fee = feeCalculator.calculateFee(duration, rate)
        val expectedReceipt = NormalReceipt(1L, 1L, 1L, entryTime, fee, exitTime)

        val actualReceipt = building.unparkVehicle(car1, exitTime)

        assertEquals(expectedReceipt, actualReceipt)
    }

    @DisplayName("should give the 1st unparked vehicles spot to a new vehicle")
    @Test
    fun reuseSpotForPark(){
        val floorsWithSize=listOf(1L)
        val car1 = Car()
        val car2 = Car()
        val entryTime1 = LocalDateTime.now()
        val building = Building(ticketBooth, receiptBooth, floorsWithSize)
        building.parkVehicle(car1, entryTime1)
        building.unparkVehicle(car1, LocalDateTime.now())
        val entryTime2 = LocalDateTime.now()
        val expectedTicket = NormalTicket(2L, 1L, 1L,entryTime2)

        val actualTicket = building.parkVehicle(car2, entryTime2)

        assertEquals(expectedTicket, actualTicket)
    }

    @DisplayName("should park a vehicle for multiple hours")
    @Test
    fun parkForMultipleHours() {
        val car1 = Car()
        val floorsWithSize=listOf(1L)
        val entryTime = LocalDateTime.now().minusDays(2)
        val exitTime = LocalDateTime.now()
        val duration = Duration.between(entryTime, exitTime).toHours()
        val building = Building(ticketBooth, receiptBooth, floorsWithSize)
        building.parkVehicle(car1, entryTime)
        val expectedReceipt = NormalReceipt(
            1L,
            1L,
            1L,
            entryTime,
            duration * feeModel.getRate(),
            exitTime
        )

        val actualReceipt = building.unparkVehicle(car1, exitTime)

        assertEquals(expectedReceipt, actualReceipt)
    }

    @DisplayName("should get a null ticket")
    @Test
    fun getANullTicket() {
        val car1 = Car()
        val floorsWithSize=listOf(0L)
        val entryTime = LocalDateTime.now()
        val building = Building(ticketBooth, receiptBooth, floorsWithSize)

        val actualTicket = building.parkVehicle(car1, entryTime)

        assertEquals(true, actualTicket.isNull())
    }

    @DisplayName("should get a null receipt")
    @Test
    fun getANullReceipt() {
        val car1 = Car()
        val floorsWithSize=listOf(1L)
        val entryTime = LocalDateTime.now()
        val exitTime = LocalDateTime.now()
        val building = Building(ticketBooth, receiptBooth, floorsWithSize)

        car1.setTicketTo(NormalTicket(1L, 1L, 1L, entryTime))
        val actualReceipt = building.unparkVehicle(car1, exitTime)

        assertEquals(true, actualReceipt.isNull())
    }

    @DisplayName("should throw an invalid ticket exception")
    @Test
    fun getInvalidTicket(){
        val car = Car()
        val floorsWithSize = listOf(-1L)
        val building = Building(ticketBooth, receiptBooth, floorsWithSize)
        car.setTicketTo(NormalTicket(1L, -1L, -1L, LocalDateTime.now()))

        val expectedErrorMessage = "Invalid ticket"
        assertThrows<InvalidTicketException>(expectedErrorMessage){building.unparkVehicle(car, LocalDateTime.now())}
    }

    @DisplayName("should throw an invalid floor exception")
    @Test
    fun getInvalidFloor(){
        val car = Car()
        val floorsWithSize = listOf(3L)
        val entryTime = LocalDateTime.now()
        val building = Building(ticketBooth, receiptBooth, floorsWithSize)
        building.parkVehicle(car, entryTime)
        car.setTicketTo(NormalTicket(1L, 2L, 1L, entryTime))

        val expectedErrorMessage = "Floor does not exist"
        assertThrows<FloorDoesNotExistException>(expectedErrorMessage){building.unparkVehicle(car, LocalDateTime.now())}
    }

    @DisplayName("should throw ticket does not exist exception")
    @Test
    fun getTicketDoesNotExist(){
        val car = Car()
        val floorsWithSize = listOf(3L)
        val building = Building(ticketBooth, receiptBooth, floorsWithSize)
        val exitTime = LocalDateTime.now()

        val expectedErrorMessage = "Ticket does not exist"
        assertThrows<TicketDoesNotExistException>(expectedErrorMessage){building.unparkVehicle(car, exitTime)}
    }

}