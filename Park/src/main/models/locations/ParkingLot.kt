package main.models.locations

import main.models.*
import main.models.vehicles.Vehicle
import java.time.LocalDateTime

class ParkingLot(override val ticketBooth: TicketBooth, override val receiptBooth: ReceiptBooth) : Location {
    override fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket? {
        val ticket = Ticket(1L, 1L, entryTime)
        vehicle.setTicketTo(ticket)
        return Ticket(1L, 1L, entryTime)
    }

    override fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt? {
        val ticket = vehicle.getVehicleTicket()?:return null
        return receiptBooth.getReceipt(ticket, exitTime)
    }
}