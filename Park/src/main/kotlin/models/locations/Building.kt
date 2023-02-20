package models.locations

import exceptions.FloorDoesNotExistException
import models.*
import models.receipts.NullReceipt
import models.receipts.Receipt
import models.tickets.NullTicket
import models.tickets.Ticket
import models.vehicles.Vehicle
import java.time.LocalDateTime

class Building(private val ticketBooth: TicketBooth, private val receiptBooth: ReceiptBooth, private val floorTracker: FloorTracker) :
    Location {

    private fun setSpotAndGetTicket(
        floor: Floor,
        spot: Spot,
        vehicle: Vehicle,
        entryTime: LocalDateTime
    ): Ticket {
        floor.setSpotTo(spot.getSpotsNumber(), vehicle)
        val ticket = ticketBooth.getTicket(
            floor.getFloorNumber(),
            spot.getSpotsNumber(),
            entryTime
        )
        vehicle.setTicketTo(ticket)
        return ticket
    }

    override fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket {
        val floor = floorTracker.getNextAvailableFloor() ?: return NullTicket()
        val spot = floor.getNextAvailableSpot() ?: return NullTicket()

        return setSpotAndGetTicket(floor, spot, vehicle, entryTime)
    }

    private fun clearSpotAndGetReceipt(
        ticket: Ticket,
        floor: Floor,
        spotNumber: Int,
        vehicle: Vehicle,
        exitTime: LocalDateTime
    ): Receipt {
        if (floor.clearSpot(spotNumber)) {
            vehicle.clearTicket()
            return receiptBooth.getReceipt(ticket, exitTime)
        }
        return NullReceipt()
    }

    override fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt {
        val ticket = receiptBooth.validateTicket(vehicle.getVehicleTicket())
        if (ticket.getFloorNumberForTicket() >= floorTracker.getSize()) throw FloorDoesNotExistException()
        val floor = floorTracker.getFloor(ticket.getFloorNumberForTicket())
        val spotNumber = ticket.getSpotNumberForTicket()

        return clearSpotAndGetReceipt(ticket, floor, spotNumber, vehicle, exitTime)
    }
}
