package main.models

class Vehicle{
    private var isParkedInSpot = false
    fun park(parkingLot: ParkingLot): Ticket?{
        if(!isParkedInSpot) {
            val ticket = parkingLot.reserveSpot()
            isParkedInSpot = true
            return ticket
        }
        return null
    }

    fun isParked(): Boolean{
        return isParkedInSpot
    }

}