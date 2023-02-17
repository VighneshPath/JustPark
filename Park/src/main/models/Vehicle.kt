package main.models

class Vehicle{
    private var isParkedInSpot = false
    private var currentlyParkedIn : ParkingLot? = null
    fun park(parkingLot: ParkingLot): Ticket?{
        if(!isParkedInSpot) {
            val ticket = parkingLot.reserveSpot()
            isParkedInSpot = true
            currentlyParkedIn = parkingLot
            return ticket
        }
        return null
    }

    fun isParked(): Boolean{
        return isParkedInSpot
    }

    fun unpark() {
        isParkedInSpot = false
        currentlyParkedIn = null
    }

}