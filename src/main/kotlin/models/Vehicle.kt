package models

import models.tickets.Ticket

class Vehicle(private val type: VehicleType) {
    var ticket: Ticket? = null
    fun getVehicleTicket() = ticket

    fun setTicketTo(ticket: Ticket) {
        this.ticket = ticket
    }

    fun clearTicket() {
        this.ticket = null
    }

    fun getVehicleType() = type
}