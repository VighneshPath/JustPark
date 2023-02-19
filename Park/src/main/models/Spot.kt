package main.models

import main.models.vehicles.Vehicle

class Spot{
    private var vehicle: Vehicle? = null
    fun isSpotTaken() = vehicle!=null
    fun reserveSpot(vehicle: Vehicle): Boolean{
        this.vehicle = vehicle
        return true
    }

    fun unreserveSpot(): Boolean{
        this.vehicle = null
        return true
    }
}