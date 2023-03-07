package models.locations

import models.FloorTracker
import models.ReceiptBooth
import models.TicketBooth
import models.receipts.Receipt
import models.tickets.NullTicket
import models.tickets.Ticket
import models.vehicles.Vehicle
import java.time.LocalDateTime

class Location(
    private val ticketBooth: TicketBooth,
    private val receiptBooth: ReceiptBooth,
    private val floorTracker: FloorTracker
) {

    fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket {
        val floor = floorTracker.getNextAvailableFloor(vehicle.type) ?: return NullTicket()
        val spot = floor.getNextAvailableSpot(vehicle.type) ?: return NullTicket()

        floorTracker.parkVehicleAt(floor.getFloorNumber(), spot.getSpotsNumber(), vehicle)

        val ticket = ticketBooth.getTicket(
            floor.getFloorNumber(),
            spot.getSpotsNumber(),
            entryTime
        )
        vehicle.setTicketTo(ticket)

        return ticket
    }

     fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt {
        val ticket = receiptBooth.validateTicket(vehicle.getVehicleTicket())
        val floor = floorTracker.getFloor(ticket.getFloorNumberForTicket())

        floorTracker.unparkVehicleFrom(floor.getFloorNumber(), ticket.getSpotNumberForTicket())

        val receipt = receiptBooth.getReceipt(ticket, exitTime)
        vehicle.clearTicket()

        return receipt
    }
}
