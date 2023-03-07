package models

import exceptions.InvalidExitTimeException
import models.feemodels.FeeModel
import models.feecalculators.AirportFeeCalculator
import models.feecalculators.FeeCalculator
import models.receipts.NormalReceipt
import models.tickets.NormalTicket
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class ReceiptBoothTest {
    private lateinit var receiptBooth: ReceiptBooth
    private lateinit var feeModel: FeeModel
    private lateinit var feeCalculator: FeeCalculator

    @BeforeEach
    fun resetReceiptBooth() {
        feeCalculator = AirportFeeCalculator()
        receiptBooth = ReceiptBooth(feeCalculator)
    }

    @DisplayName("should get a receipt when a ticket is provided")
    @Test
    fun shouldGetAReceiptWhenGivenATicket() {
        val ticket = NormalTicket(1L, 1, 1, LocalDateTime.now())
        val exitTime = LocalDateTime.now()
        val expectedReceipt = NormalReceipt(1L, 1, 1, ticket.getTicketEntryDateTime(), 20, exitTime)

        val actualReceipt = receiptBooth.getReceipt(ticket, exitTime)

        assertEquals(expectedReceipt, actualReceipt)
    }

    @DisplayName("should throw an exception if exit time provided is less that entry time")
    @Test
    fun shouldThrowAnErrorForInvalidExitTime() {
        val ticket = NormalTicket(1L, 1, 1, LocalDateTime.now())
        val exitTime = LocalDateTime.now().minusDays(1)
        val expectedErrorMessage = "Exit time must be later than entry time"

        assertThrows<InvalidExitTimeException>(expectedErrorMessage) { receiptBooth.getReceipt(ticket, exitTime) }
    }
}