package models

import exceptions.InvalidExitTimeException
import models.VehicleType.CAR
import models.feecalculators.FeeCalculator
import models.feecalculators.MallFeeCalculator
import models.feedata.MallFeeData
import models.receipts.NormalReceipt
import models.tickets.NormalTicket
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class ReceiptBoothTest {
    private var feeCalculator: FeeCalculator = MallFeeCalculator(MallFeeData())
    private var receiptBooth =ReceiptBooth(feeCalculator)

    @BeforeEach
    fun resetReceiptBooth() {
        feeCalculator = MallFeeCalculator(MallFeeData())
        receiptBooth = ReceiptBooth(feeCalculator)
    }

    @DisplayName("should get a receipt when a ticket is provided")
    @Test
    fun shouldGetAReceiptWhenGivenATicket() {
        val vehicle = Vehicle(CAR)
        val ticket = NormalTicket(1L, 1, 1, LocalDateTime.now())
        vehicle.setTicketTo(ticket)
        val exitTime = LocalDateTime.now()
        val expectedReceipt = NormalReceipt(1L, 1, 1, ticket.getTicketEntryDateTime(), 20, exitTime)

        val actualReceipt = receiptBooth.getReceipt(vehicle, exitTime)

        assertEquals(expectedReceipt, actualReceipt)
    }

    @DisplayName("should throw an exception if exit time provided is less that entry time")
    @Test
    fun shouldThrowAnErrorForInvalidExitTime() {
        val vehicle = Vehicle(CAR)
        val ticket = NormalTicket(1L, 1, 1, LocalDateTime.now())
        vehicle.setTicketTo(ticket)
        val exitTime = LocalDateTime.now().minusDays(1)
        val expectedErrorMessage = "Exit time must be later than entry time"

        assertThrows<InvalidExitTimeException>(expectedErrorMessage) { receiptBooth.getReceipt(vehicle, exitTime) }
    }
}