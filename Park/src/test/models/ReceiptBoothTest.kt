package test.models

import main.models.*
import org.junit.jupiter.api.Assertions.*
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
        val ticket = Ticket(1L, 1L)
        val exitTime = LocalDateTime.now()
        val expectedReceipt = Receipt(1L, 1L, ticket.getTicketEntryDateTime(), 10, exitTime)

        val actualReceipt = receiptBooth.getReceipt(ticket, exitTime)

        assertEquals(expectedReceipt, actualReceipt)
    }
}