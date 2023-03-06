package models

import exceptions.InvalidExitTimeException
import models.feecalculators.FeeCalculator
import main.models.feemodels.FeeModel
import java.time.Duration
import java.time.LocalDateTime

class ReceiptBooth(private val feeCalculator: FeeCalculator, private var feeModel: FeeModel) {
    private var receiptCounter: Long = 0
    fun getReceipt(ticket: Ticket, exitTime: LocalDateTime): Receipt {
        if(exitTime.isBefore(ticket.getTicketEntryDateTime())) throw InvalidExitTimeException()

        receiptCounter++

        val duration = Duration.between(ticket.getTicketEntryDateTime(), exitTime).toHours()

        return Receipt(receiptCounter,
            ticket.getSpotNumberForTicket(),
            ticket.getTicketEntryDateTime(),
            feeCalculator.calculateFee(duration, feeModel.getRate()),
            exitTime,
            ticket.getFloorNumberForTicket()
            )
    }
}