package models

import exceptions.InvalidExitTimeException
import exceptions.InvalidTicketException
import exceptions.TicketDoesNotExistException
import models.feecalculators.FeeCalculator
import models.receipts.NormalReceipt
import models.receipts.Receipt
import models.tickets.Ticket
import java.time.Duration
import java.time.LocalDateTime

class ReceiptBooth(private var feeCalculator: FeeCalculator) {
    private var receiptCounter: Long = 0
    fun validateTicket(ticket: Ticket?): Ticket {
        ticket ?: throw TicketDoesNotExistException()
        if (ticket.getSpotNumberForTicket() <= 0 ||
            ticket.getFloorNumberForTicket() <= 0
        ) {
            throw InvalidTicketException()
        }
        return ticket
    }

    fun getReceipt(vehicle: Vehicle, exitTime: LocalDateTime): Receipt {
        val ticket = vehicle.getVehicleTicket()!!
        if (exitTime.isBefore(ticket.getTicketEntryDateTime())) throw InvalidExitTimeException()

        receiptCounter++

        val duration = Duration.between(ticket.getTicketEntryDateTime(), exitTime).toHours()

        return NormalReceipt(
            receiptCounter,
            ticket.getFloorNumberForTicket(),
            ticket.getSpotNumberForTicket(),
            ticket.getTicketEntryDateTime(),
            feeCalculator.getFinalPrice(duration, vehicle.getVehicleType()),
            exitTime
        )
    }
}