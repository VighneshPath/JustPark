package models

class Spot(private var spotNumber: Int, private var spotForVehicleType: VehicleType) {
    private var vehicle: Vehicle? = null

    fun getSpotVehicleType() = spotForVehicleType

    fun getSpotsNumber() = spotNumber

    fun isSpotTaken() = vehicle != null

    fun reserveSpot(vehicle: Vehicle) {
        this.vehicle = vehicle
    }

    fun unreserveSpot() {
        this.vehicle = null
    }
}