package models.vehicles

import models.VehicleType
import models.tickets.Ticket

interface Vehicle {
    val type: VehicleType
    var ticket: Ticket?

    fun getVehicleTicket(): Ticket?
    fun setTicketTo(ticket: Ticket)

    fun clearTicket()
}