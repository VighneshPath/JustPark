package models.locations

import exceptions.TicketDoesNotExistException
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

    @DisplayName("should create a building with one floor and park unpark multiple vehicles to get a receipt")
    @Test
    fun createBuildingWithOneFloorAndUnparkMultipleVehicles(){
        val floors = listOf(1L, 1L)
        val building: Building = Airport(ticketBooth, receiptBooth, floors)
        val vehicle: Vehicle = Car()
        val vehicle1: Vehicle = Car()
        val entryTime = LocalDateTime.now()
        building.parkVehicle(vehicle, entryTime)
        building.parkVehicle(vehicle1, entryTime)
        val exitTime = LocalDateTime.now()
        val duration = Duration.between(exitTime, entryTime).toHours()
        val expectedReceipt = Receipt(1L,
            1L,
            entryTime,
            feeCalculator.calculateFee(duration, feeModel.getRate()),
            exitTime,
            2
        )

        val actualReceipt = building.unparkVehicle(vehicle1, exitTime)

        assertEquals(expectedReceipt, actualReceipt)
    }

    @DisplayName("should give the spot and floor of the 1st unparked vehicle to the latest parking vehicle")
    @Test
    fun createBuildingWithOneFloorAndUnparkMultipleVehiclesWhileReusingSpots(){
        val floors = listOf(1L, 1L)
        val building: Building = Airport(ticketBooth, receiptBooth, floors)
        val vehicle: Vehicle = Car()
        val vehicle1: Vehicle = Car()
        val vehicle2: Vehicle = Car()
        val entryTime = LocalDateTime.now()
        building.parkVehicle(vehicle, entryTime)
        building.parkVehicle(vehicle1, entryTime)
        building.unparkVehicle(vehicle, entryTime)
        val expectedTicket = Ticket(3L, 1L, entryTime, 1)

        val actualTicket = building.parkVehicle(vehicle2, entryTime)

        assertEquals(expectedTicket, actualTicket)
    }
    @DisplayName("should park a vehicle for multiple hours")
    @Test
    fun parkVehicleForMultipleHours(){
        val floors = listOf(1L, 1L)
        val building: Building = Airport(ticketBooth, receiptBooth, floors)
        val vehicle: Vehicle = Car()
        val entryTime = LocalDateTime.now().minusDays(2)
        building.parkVehicle(vehicle, entryTime)
        val exitTime = LocalDateTime.now()
        val duration = Duration.between(entryTime, exitTime).toHours()
        val expectedReceipt = Receipt(
            1L,
            1L,
            entryTime,
            duration * feeModel.getRate(),
            exitTime
        )

        val actualReceipt = building.unparkVehicle(vehicle, exitTime)

        assertEquals(expectedReceipt, actualReceipt)
    }

    @DisplayName("should throw an error if a vehicle without a ticket tries to unpark")
    @Test
    fun unparkVehicleWithoutTicket(){
        val floors = listOf(1L, 1L)
        val building: Building = Airport(ticketBooth, receiptBooth, floors)
        val car = Car()
        val exitTime = LocalDateTime.now()
        val errorMessage = "Ticket does not exist"

        org.junit.jupiter.api.assertThrows<TicketDoesNotExistException>(errorMessage) {
            building.unparkVehicle(
                car,
                exitTime
            )
        }
    }
}