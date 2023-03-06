package models.locations

import main.models.*
import main.models.vehicles.Vehicle
import java.time.LocalDateTime

interface Building{
    val ticketBooth: TicketBooth
    val receiptBooth: ReceiptBooth
    val floorList: List<Floor>

    fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket?
    fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt?
}