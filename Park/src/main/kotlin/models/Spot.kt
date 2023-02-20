package models

import models.vehicles.Vehicle

class Spot(private var spotNumber: Int) {
    private var vehicle: Vehicle? = null
    fun getSpotsNumber(): Int {
        return spotNumber
    }

    fun isSpotTaken() = vehicle != null
    fun reserveSpot(vehicle: Vehicle): Boolean {
        this.vehicle = vehicle
        return true
    }

    fun unreserveSpot(): Boolean {
        if (this.vehicle == null) return false
        this.vehicle = null
        return true
    }
}