package models.locations

import models.vehicles.Vehicle
import models.*
import java.time.LocalDateTime

interface Location {
    val ticketBooth: TicketBooth
    val receiptBooth: ReceiptBooth
    val floor: Floor
    fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket?
    fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt?
}