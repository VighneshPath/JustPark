package models.locations

import models.vehicles.Vehicle
import models.*
import models.tickets.Ticket
import java.time.LocalDateTime

interface Location {
    fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket?
    fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt?
}