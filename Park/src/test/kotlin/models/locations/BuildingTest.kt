package models.locations

import main.models.Floor
import main.models.ReceiptBooth
import main.models.Ticket
import main.models.TicketBooth
import main.models.feecalculators.FeeCalculator
import main.models.feecalculators.HourlyFeeCalculator
import main.models.feemodels.CarForParkingLotFeeModel
import main.models.feemodels.FeeModel
import main.models.vehicles.Car
import main.models.vehicles.Vehicle
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class BuildingTest{
    private var feeCalculator: FeeCalculator = HourlyFeeCalculator()
    private var feeModel: FeeModel = CarForParkingLotFeeModel()
    private var receiptBooth = ReceiptBooth(feeCalculator, feeModel)
    private var ticketBooth = TicketBooth()

    @DisplayName("should create a building with one floor and park a vehicle in it")
    @Test
    fun createBuildingWithOneFloor(){
        val floors = listOf(Floor(2))
        val building: Building = Airport(ticketBooth, receiptBooth, floors)
        val vehicle: Vehicle = Car()
        val entryTime = LocalDateTime.now()
        val expectedTicket = Ticket(1L, 1L, entryTime, 1L)

        val actualTicket = building.parkVehicle(vehicle, entryTime)

        assertEquals(expectedTicket, actualTicket)
    }
}