package main.models

import main.constants.VEHICLE_SPOT_LIMIT

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
        return Receipt(receiptCounter, ticket!!.getTicketSpotNumber(), ticket.getTicketEntryDateTime())
    }
}