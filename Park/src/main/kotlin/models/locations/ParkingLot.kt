package main.models.locations

import main.exceptions.TicketDoesNotExistException
import main.models.*
import main.models.vehicles.Vehicle
import java.time.LocalDateTime

class ParkingLot(override val ticketBooth: TicketBooth,
                 override val receiptBooth: ReceiptBooth,
                 override val floor: Floor
) : Location {
    override fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket? {
        val spot = floor.getNextAvailableSpot()
        if (spot != null) {
            floor.setSpotTo(spot.getSpotsNumber(), vehicle)
            val ticket = ticketBooth.getTicket(spot.getSpotsNumber(), entryTime)
            vehicle.setTicketTo(ticket)
            return ticket
        }
        return null
    }

    override fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt? {
        val ticket = vehicle.getVehicleTicket() ?: throw TicketDoesNotExistException()
        if (floor.clearSpot(ticket.getSpotNumberForTicket())) {
            vehicle.clearTicket()
            return receiptBooth.getReceipt(ticket, exitTime)
        }
        return null
    }
}