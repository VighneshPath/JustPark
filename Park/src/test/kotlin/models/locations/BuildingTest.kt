package models.locations

import models.ReceiptBooth
import models.Ticket
import models.TicketBooth
import models.feecalculators.FeeCalculator
import models.feecalculators.HourlyFeeCalculator
import models.feemodels.CarForParkingLotFeeModel
import models.feemodels.FeeModel
import models.vehicles.Car
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
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
        val expectedTicket = Ticket(1L, 1L, 1L, entryTime)

        val actualTicket = building.parkVehicle(car, entryTime)

        assertEquals(expectedTicket, actualTicket)
    }
}