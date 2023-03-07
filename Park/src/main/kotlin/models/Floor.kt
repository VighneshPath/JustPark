package models

import constants.VEHICLE_SPOT_LIMIT
import exceptions.SpotDoesNotExistException
import models.vehicles.Vehicle

class Floor(
    private val floorNumber: Int,
    vehicleLimits: Map<VehicleType, Int> = mapOf(VehicleType.CAR to 1)
) {
    private var spots: MutableList<Spot> = mutableListOf()

    init {
        spots.add(Spot(0))
        vehicleLimits.forEach{
            for(typeCount in 0 until it.value){
                spots.add(Spot(spots.size, it.key))
            }
        }
    }

    fun getFloorNumber() = floorNumber

    fun isFull(vehicleType: VehicleType) = getNextAvailableSpot(vehicleType) == null

    fun getNextAvailableSpot(vehicleType: VehicleType): Spot? {
        for (spot in 1 until spots.size) {
            if (!spots[spot].isSpotTaken() && spots[spot].getSpotVehicleType() == vehicleType) return spots[spot]
        }
        return null
    }

    private fun checkSpot(spotNumber: Int) {
        if (spotNumber >= spots.size || spotNumber <= 0) {
            throw SpotDoesNotExistException()
        }
    }

    private fun isSpotTaken(spotNumber: Int): Boolean {
        checkSpot(spotNumber)
        return spots[spotNumber].isSpotTaken()
    }

    fun setSpotTo(spotNumber: Int, vehicle: Vehicle): Boolean {
        if (!isSpotTaken(spotNumber)) {
            spots[spotNumber].reserveSpot(vehicle)
            return true
        }
        return false
    }

    fun clearSpot(spotNumber: Int) {
        checkSpot(spotNumber)
        spots[spotNumber].unreserveSpot()
    }
}