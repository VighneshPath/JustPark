package main.models

import main.constants.FEE_PER_HOUR
import main.constants.VEHICLE_SPOT_LIMIT
import java.time.Duration
import java.time.LocalDateTime

class ParkingLot {
    private var ticketCounter = 0L // Only positive, always increasing
    private var receiptCounter = 0L
    private var spotStatus: MutableList<Boolean> = MutableList<Boolean>(VEHICLE_SPOT_LIMIT) { false }

    private fun getNextSpot(): Int{
        for(spot in spotStatus.indices){
            if(!spotStatus[spot]) return spot
        }
        return -1
    }

    private fun removeSpot(spot: Int): Boolean{
        if(spotStatus[spot]){
            spotStatus[spot] = false
            return true
        }
        return false
    }

    fun reserveSpot(): Ticket?{
        val spot = getNextSpot()
        if(spot!=-1){
            spotStatus[spot] = true
            ticketCounter+=1

            return Ticket(ticketCounter, spot.toLong())
        }
        return null
    }

    fun unreserveSpot(ticket: Ticket?): Receipt {
        receiptCounter+=1
        val exitTime = LocalDateTime.now()
        val spot = ticket!!.getSpotNumberForTicket().toInt()
        removeSpot(spot)
        val duration = Duration.between(ticket.getTicketEntryDateTime(), exitTime).toHours()
        return Receipt(receiptCounter, ticket.getTicketSpotNumber(), ticket.getTicketEntryDateTime(), FEE_PER_HOUR*duration)
    }
}