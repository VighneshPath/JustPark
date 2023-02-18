package main.models.vehicles

import main.models.Ticket
import main.models.VehicleType

interface Vehicle{
    val type: VehicleType
    var ticket: Ticket?
}