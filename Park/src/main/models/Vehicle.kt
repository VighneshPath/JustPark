package main.models

class Vehicle {
    private var isParkedInSpot = false
    fun park() {
        isParkedInSpot = true
    }

    fun isParked(): Boolean{
        return isParkedInSpot
    }

}