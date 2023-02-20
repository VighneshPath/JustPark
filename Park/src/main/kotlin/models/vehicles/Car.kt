package models.vehicles

import models.tickets.Ticket
import models.VehicleType
import models.VehicleType.CAR

class Car : Vehicle {
    override val type: VehicleType = CAR
    override var ticket: Ticket? = null
    override fun getVehicleTicket(): Ticket? {
        return ticket
    }

    override fun setTicketTo(ticket: Ticket): Boolean {
        this.ticket = ticket
        return true
    }

    override fun clearTicket(): Boolean {
        this.ticket = null
        return true
    }
}