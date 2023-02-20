package models.locations

import models.ReceiptBooth
import models.TicketBooth
import models.feecalculators.HourlyFeeCalculator
import models.feemodels.CarForParkingLotFeeModel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BuildingTest{
    @DisplayName("should create a building with one floor")
    @Test
    fun createBuildingWithOneFloor(){
        val feeCalculator = HourlyFeeCalculator()
        val feeModel = CarForParkingLotFeeModel()
        val receiptBooth = ReceiptBooth(feeCalculator, feeModel)
        val ticketBooth = TicketBooth()
        val floorsWithSize = listOf(10L)
        val building = Building(ticketBooth, receiptBooth, floorsWithSize)

        val floor = building.getNextAvailableFloor()!!

        assertEquals(1, floor.getFloorNumber())
    }
}