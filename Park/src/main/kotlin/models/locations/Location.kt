package models.locations

import models.receipts.Receipt
import models.tickets.Ticket
import models.vehicles.Vehicle
import java.time.LocalDateTime

interface Location {
    fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket?
    fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt?
}