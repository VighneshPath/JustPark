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
import java.time.Duration
import java.time.LocalDateTime

class ParkingLotTest{
    private lateinit var receiptBooth: ReceiptBooth
    private lateinit var ticketBooth: TicketBooth
    private lateinit var feeCalculator: FeeCalculator
    private lateinit var feeModel: FeeModel
    private lateinit var parkingLot: ParkingLot
    private lateinit var spotTracker: SpotTracker
    @BeforeEach
    fun setBoothsAndParkingLot(){
        feeCalculator = HourlyFeeCalculator()
        feeModel = CarForParkingLotFeeModel()
        receiptBooth = ReceiptBooth(feeCalculator, feeModel)
        ticketBooth = TicketBooth()
        val totalSpots = 100L
        spotTracker = SpotTracker(totalSpots)

        parkingLot = ParkingLot(ticketBooth, receiptBooth, spotTracker)
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

    @Test
    @DisplayName("should unpark a vehicle")
    fun unparkAVehicle(){
        val car = Car()
        val entryTime = LocalDateTime.now()
        parkingLot.parkVehicle(car, entryTime)
        val rate = feeModel.getRate()
        val exitTime = LocalDateTime.now()
        val duration = Duration.between(exitTime, entryTime).toHours()
        val expectedReceipt = Receipt(1L,
            1L,
            entryTime,
            feeCalculator.calculateFee(duration, rate),
            exitTime
        )

        val actualReceipt = parkingLot.unparkVehicle(car, exitTime)

        assertEquals(expectedReceipt, actualReceipt)
    }

    @DisplayName("should park multiple vehicles")
    @Test
    fun parkMultiple(){
        val car1 = Car()
        val car2 = Car()
        val entryTime = LocalDateTime.now()
        val expectedTicket1 = Ticket(1L, 1L, entryTime)
        val expectedTicket2 = Ticket(2L, 2L, entryTime)

        val actualTicket1 = parkingLot.parkVehicle(car1, entryTime)
        val actualTicket2 = parkingLot.parkVehicle(car2, entryTime)

        assertEquals(expectedTicket1, actualTicket1)
        assertEquals(expectedTicket2, actualTicket2)
    }

    @DisplayName("should unpark multiple vehicles")
    @Test
    fun unparkMultiple(){
        val car1 = Car()
        val car2 = Car()
        val entryTime = LocalDateTime.now()
        parkingLot.parkVehicle(car1, entryTime)
        parkingLot.parkVehicle(car2, entryTime)
        val exitTime = LocalDateTime.now()
        val duration = Duration.between(exitTime, entryTime).toHours()
        val rate = feeModel.getRate()
        val expectedReceipt1 = Receipt(1L,
            1L,
            entryTime,
            feeCalculator.calculateFee(duration, rate),
            exitTime
        )
        val expectedReceipt2 = Receipt(2L,
            2L,
            entryTime,
            feeCalculator.calculateFee(duration, rate),
            exitTime
        )

        val actualReceipt1 = parkingLot.unparkVehicle(car1, exitTime)
        val actualReceipt2 = parkingLot.unparkVehicle(car2, exitTime)

        assertEquals(expectedReceipt1, actualReceipt1)
        assertEquals(expectedReceipt2, actualReceipt2)
    }

    @DisplayName("should give the 1st unparked vehicles spot to a new vehicle")
    @Test
    fun reuseSpotForPark(){
        val car1 = Car()
        val car2 = Car()
        val entryTime1 = LocalDateTime.now()
        parkingLot.parkVehicle(car1, entryTime1)
        parkingLot.unparkVehicle(car1, LocalDateTime.now())
        val entryTime2 = LocalDateTime.now()
        val expectedTicket = Ticket(2L, 1L, entryTime2)

        val actualTicket = parkingLot.parkVehicle(car2, entryTime2)

        assertEquals(expectedTicket, actualTicket)
    }
}