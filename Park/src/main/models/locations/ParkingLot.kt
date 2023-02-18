package main.models.locations

import main.models.*
import main.models.vehicles.Vehicle
import java.time.LocalDateTime

class ParkingLot(override val ticketBooth: TicketBooth, override val receiptBooth: ReceiptBooth) : Location {
    override fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket {
        return Ticket(1L, 1L, entryTime)
    }

    override fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt {
        TODO("Not yet implemented")
    }
}