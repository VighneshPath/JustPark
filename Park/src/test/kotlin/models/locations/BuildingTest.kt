package models.locations

import models.Floor
import models.ReceiptBooth
import models.Ticket
import main.models.TicketBooth
import models.feecalculators.FeeCalculator
import models.feecalculators.HourlyFeeCalculator
import models.feemodels.CarForParkingLotFeeModel
import main.models.feemodels.FeeModel
import models.Receipt
import models.vehicles.Car
import models.vehicles.Vehicle
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalDateTime

class BuildingTest{
    private var feeCalculator: FeeCalculator = HourlyFeeCalculator()
    private var feeModel: FeeModel = CarForParkingLotFeeModel()
    private var receiptBooth = ReceiptBooth(feeCalculator, feeModel)
    private var ticketBooth = TicketBooth()

    @DisplayName("should create a building with one floor and park a vehicle in it")
    @Test
    fun createBuildingWithOneFloor(){
        val floorSizes = listOf(2L)
        val building: Building = Airport(ticketBooth, receiptBooth, floorSizes)
        val vehicle: Vehicle = Car()
        val entryTime = LocalDateTime.now()
        val expectedTicket = Ticket(1L, 1L, entryTime, 1)

        val actualTicket = building.parkVehicle(vehicle, entryTime)

        assertEquals(expectedTicket, actualTicket)
    }

    @DisplayName("should create a building with two floors and park vehicle in different floors")
    @Test
    fun createBuildingWithMultipleFloorsAndParkInEach(){
        val floors = listOf(1L, 1L)
        val building: Building = Airport(ticketBooth, receiptBooth, floors)
        val vehicle: Vehicle = Car()
        val vehicle1: Vehicle = Car()
        val entryTime = LocalDateTime.now()
        val expectedTicket = Ticket(2L, 1L , entryTime, 2)
        building.parkVehicle(vehicle, entryTime)

        val actualTicket = building.parkVehicle(vehicle1, entryTime)

        assertEquals(expectedTicket, actualTicket)
    }

    @DisplayName("should create a building with one floor and park unpark a vehicle to get a receipt")
    @Test
    fun createBuildingWithOneFloorAndUnparkAVehicle(){
        val floorSizes = listOf(2L)
        val building: Building = Airport(ticketBooth, receiptBooth, floorSizes)
        val vehicle: Vehicle = Car()
        val entryTime = LocalDateTime.now()
        building.parkVehicle(vehicle, entryTime)
        val exitTime = LocalDateTime.now()
        val duration = Duration.between(exitTime, entryTime).toHours()
        val expectedReceipt = Receipt(1L,
            1L,
            entryTime,
            feeCalculator.calculateFee(duration, feeModel.getRate()),
            exitTime,
            1
        )

        val actualReceipt = building.unparkVehicle(vehicle, exitTime)

        assertEquals(expectedReceipt, actualReceipt)
    }
}