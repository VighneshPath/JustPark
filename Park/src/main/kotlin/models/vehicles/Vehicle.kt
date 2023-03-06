package models.vehicles

import models.Ticket
import models.VehicleType

interface Vehicle{
    val type: VehicleType
    var ticket: Ticket?

    fun getVehicleTicket(): Ticket?
    fun setTicketTo(ticket: Ticket): Boolean

    fun clearTicket(): Boolean
}