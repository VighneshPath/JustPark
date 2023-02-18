package main.models.locations

import main.models.*
import main.models.vehicles.Vehicle
import java.time.LocalDateTime

interface Location {
    val ticketBooth: TicketBooth
    val receiptBooth: ReceiptBooth
    fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket
    fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt
}