package models

import models.vehicles.Vehicle

class Spot(private var spotNumber: Int) {
    private var vehicle: Vehicle? = null
    fun getSpotsNumber(): Int {
        return spotNumber
    }

    fun isSpotTaken() = vehicle != null

    fun reserveSpot(vehicle: Vehicle) {
        this.vehicle = vehicle
    }

    fun unreserveSpot() {
        this.vehicle = null
    }
}