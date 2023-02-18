package test.models

import main.models.*
import main.models.feecalculators.FeeCalculator
import main.models.feecalculators.HourlyFeeCalculator
import main.models.feemodels.CarForParkingLotFeeModel
import main.models.feemodels.FeeModel
import main.models.locations.ParkingLot
import main.models.vehicles.Car
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ParkingLotTest{
    private lateinit var receiptBooth: ReceiptBooth
    private lateinit var ticketBooth: TicketBooth
    private lateinit var feeCalculator: FeeCalculator
    private lateinit var feeModel: FeeModel
    private lateinit var parkingLot: ParkingLot
    @BeforeEach
    fun setBoothsAndParkingLot(){
        feeCalculator = HourlyFeeCalculator()
        feeModel = CarForParkingLotFeeModel()
        receiptBooth = ReceiptBooth(feeCalculator, feeModel)
        ticketBooth = TicketBooth()

        parkingLot = ParkingLot(ticketBooth, receiptBooth)
    }
    @Test
    @DisplayName("should park a vehicle")
    fun parkAVehicle(){
        val car = Car()
        val entryTime = LocalDateTime.now()
        val expectedTicket = Ticket(1L, 1L, entryTime)

        val actualTicket = parkingLot.parkVehicle(car, entryTime)

        assertEquals(expectedTicket, actualTicket)
    }
}