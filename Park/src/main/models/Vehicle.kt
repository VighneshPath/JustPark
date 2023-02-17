package main.models

class Vehicle {
    private var isParkedInSpot = false
    fun park(): Ticket{
        isParkedInSpot = true
        return Ticket(1, 1)
    }

    fun isParked(): Boolean{
        return isParkedInSpot
    }

}