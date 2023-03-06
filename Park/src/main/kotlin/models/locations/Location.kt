package models.locations

import main.models.*
import models.Floor
import models.Receipt
import models.ReceiptBooth
import models.Ticket
import models.vehicles.Vehicle
import java.time.LocalDateTime

interface Location {
    val ticketBooth: TicketBooth
    val receiptBooth: ReceiptBooth
    val floor: Floor
    fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket?
    fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt?
}