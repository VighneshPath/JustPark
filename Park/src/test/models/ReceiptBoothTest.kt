package test.models

import main.exceptions.InvalidExitTimeException
import main.models.*
import main.models.feecalculators.FeeCalculator
import main.models.feecalculators.HourlyFeeCalculator
import main.models.feemodels.CarForParkingLotFeeModel
import main.models.feemodels.FeeModel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ReceiptBoothTest{
    private lateinit var receiptBooth: ReceiptBooth
    private lateinit var feeCalculator: FeeCalculator
    private lateinit var feeModel: FeeModel

    @BeforeEach
    fun resetReceiptBooth(){
        feeCalculator = HourlyFeeCalculator()
        feeModel = CarForParkingLotFeeModel()
        receiptBooth = ReceiptBooth(feeCalculator, feeModel)
    }
    @DisplayName("should get a receipt when a ticket is provided")
    @Test
    fun shouldGetAReceiptWhenGivenATicket(){
        val ticket = Ticket(1L, 1L, LocalDateTime.now())
        val exitTime = LocalDateTime.now()
        val expectedReceipt = Receipt(1L, 1L, ticket.getTicketEntryDateTime(), 10, exitTime)

        val actualReceipt = receiptBooth.getReceipt(ticket, exitTime)

        assertEquals(expectedReceipt, actualReceipt)
    }

    @DisplayName("should throw an exception if exit time provided is less that entry time")
    @Test
    fun shouldThrowAnErrorForInvalidExitTime(){
        val ticket = Ticket(1L, 1L, LocalDateTime.now())
        val exitTime = LocalDateTime.now().minusDays(1)
        val expectedErrorMessage = "Exit time must be later than entry time"

        assertThrows<InvalidExitTimeException>(expectedErrorMessage){receiptBooth.getReceipt(ticket, exitTime)}
    }
}