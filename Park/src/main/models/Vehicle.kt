package main.models

import main.exceptions.VehicleAlreadyParkedException
import main.exceptions.VehicleIsNotParkedException

class Vehicle{
    private var isParkedInSpot = false
    private var currentlyParkedIn : ParkingLot? = null
    private var ticket: Ticket? = null
    fun park(parkingLot: ParkingLot): Ticket{
        if(!isParkedInSpot) {
            val ticket = parkingLot.reserveSpot()
            this.ticket = ticket
            isParkedInSpot = true
            currentlyParkedIn = parkingLot
            return ticket
        }
        throw VehicleAlreadyParkedException()
    }

    fun isParked(): Boolean{
        return isParkedInSpot
    }

    fun unpark() : Receipt {
        if(isParkedInSpot){
            val receipt = currentlyParkedIn!!.unreserveSpot(ticket!!)
            isParkedInSpot = false
            currentlyParkedIn = null
            ticket = null
            return receipt
        }
        throw VehicleIsNotParkedException()
    }

}