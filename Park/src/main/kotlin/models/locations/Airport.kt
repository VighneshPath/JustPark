package models.locations

import main.models.*
import main.models.vehicles.Vehicle
import java.time.LocalDateTime

class Airport(
    override val ticketBooth: TicketBooth, override val receiptBooth: ReceiptBooth,
    override val floorList: List<Floor>
) : Building {
    override fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket? {
        return Ticket(1L, 1L, entryTime, 1L)
    }

    override fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt? {
        TODO("Not yet implemented")
    }

}