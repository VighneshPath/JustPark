package models

import models.VehicleType
import models.VehicleType.CAR
import models.tickets.Ticket

class Vehicle(private val type: VehicleType){
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

    fun getVehicleType(): VehicleType {
        return type
    }
}