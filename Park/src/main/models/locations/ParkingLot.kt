package main.models.locations

import main.exceptions.TicketDoesNotExistException
import main.models.*
import main.models.vehicles.Vehicle
import java.time.LocalDateTime

class ParkingLot(override val ticketBooth: TicketBooth,
                 override val receiptBooth: ReceiptBooth,
                 override val spotTracker: SpotTracker
) : Location {
    override fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket? {
        val spot = spotTracker.getNextAvailableSpot()
        if (spot != null) {
            spotTracker.setSpotTo(spot.getSpotsNumber(), vehicle)
            val ticket = ticketBooth.getTicket(spot.getSpotsNumber(), entryTime)
            if (vehicle.setTicketTo(ticket)) {
                return ticket
            }
        }
        return null
    }

    override fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt? {
        val ticket = vehicle.getVehicleTicket() ?: throw TicketDoesNotExistException()
        if (spotTracker.clearSpot(ticket.getSpotNumberForTicket())) {
            return receiptBooth.getReceipt(ticket, exitTime)
        }
        return null
    }
}