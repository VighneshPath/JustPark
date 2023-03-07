package models.vehicles

import models.VehicleType
import models.VehicleType.CAR
import models.tickets.Ticket

class Vehicle(val type: VehicleType){
    var ticket: Ticket? = null
    fun getVehicleTicket(): Ticket? {
        return ticket
    }

    fun setTicketTo(ticket: Ticket) {
        this.ticket = ticket
    }

    fun clearTicket() {
        this.ticket = null
    }
}