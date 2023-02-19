package main.models.vehicles

import main.models.Ticket
import main.models.VehicleType
import main.models.VehicleType.CAR

class Car() : Vehicle {
    override val type: VehicleType = CAR
    override var ticket: Ticket? = null
    override fun getVehicleTicket(): Ticket? {
        return ticket
    }

    override fun setTicketTo(ticket: Ticket): Boolean {
        this.ticket = ticket
        return true
    }
}