package models

import models.VehicleType.CAR
import models.vehicles.Vehicle

class Spot(private var spotNumber: Int, private var spotForVehicleType: VehicleType = CAR) {
    private var vehicle: Vehicle? = null

    fun getSpotVehicleType(): VehicleType{
        return spotForVehicleType
    }

    fun setSpotVehicleType(vehicleType: VehicleType){
        spotForVehicleType = vehicleType
    }

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