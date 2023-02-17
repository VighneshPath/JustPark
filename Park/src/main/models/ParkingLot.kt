package main.models

import main.constants.FEE_PER_HOUR
import main.constants.VEHICLE_SPOT_LIMIT
import java.time.Duration
import java.time.LocalDateTime

class ParkingLot {
    private var spotCounter = 0L
    private var ticketCounter = 0L // Only positive, always increasing
    private var receiptCounter = 0L

    fun reserveSpot(): Ticket?{
        if(spotCounter < VEHICLE_SPOT_LIMIT){
            spotCounter+=1
            ticketCounter+=1

            return Ticket(spotCounter, ticketCounter)
        }
        return null
    }

    fun unreserveSpot(ticket: Ticket?): Receipt {
        receiptCounter+=1
        val exitTime = LocalDateTime.now()
        val duration = Duration.between(ticket!!.getTicketEntryDateTime(), exitTime).toHours()
        return Receipt(receiptCounter, ticket.getTicketSpotNumber(), ticket.getTicketEntryDateTime(), FEE_PER_HOUR*duration)
    }
}