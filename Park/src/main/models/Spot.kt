package main.models

import main.models.vehicles.Vehicle

data class Spot(private var spotNumber: Long){
    private var vehicle: Vehicle? = null
    fun getSpotsNumber(): Long{
        return spotNumber
    }
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