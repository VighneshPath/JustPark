package models.vehicles

import models.VehicleType
import models.VehicleType.CAR
import models.tickets.Ticket

class Car : Vehicle {
    override val type: VehicleType = CAR
    override var ticket: Ticket? = null
    override fun getVehicleTicket(): Ticket? {
        return ticket
    }

    override fun setTicketTo(ticket: Ticket) {
        this.ticket = ticket
    }

    override fun clearTicket() {
        this.ticket = null
    }
}