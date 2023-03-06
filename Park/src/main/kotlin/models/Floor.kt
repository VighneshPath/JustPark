package models

import constants.VEHICLE_SPOT_LIMIT
import exceptions.SpotDoesNotExistException
import models.vehicles.Vehicle

class Floor(
    private val floorNumber: Int,
    private val totalSpots: Int = VEHICLE_SPOT_LIMIT
) {
    private var spots: MutableList<Spot> = mutableListOf()

    init {
        for (spot in 0..totalSpots) {
            spots.add(Spot(spot))
        }
    }

    fun setSpotsTypes(typeWiseLimit: Map<VehicleType, Int>): Map<VehicleType, Int> {
        var spotCounter = 1
        val updatedTypeWiseCount = typeWiseLimit.toMutableMap()

        typeWiseLimit.forEach{
            for(spotCountForVehicleType in 0 until it.value){
                if(spotCounter >= spots.size){
                    return updatedTypeWiseCount
                }
                spots[spotCounter].setSpotVehicleType(it.key)
                spotCounter++
                updatedTypeWiseCount[it.key] = updatedTypeWiseCount[it.key]!! - 1
            }
        }

        return updatedTypeWiseCount
    }

    fun getFloorNumber() = floorNumber

    fun isFull(vehicleType: VehicleType) = getNextAvailableSpot(vehicleType) == null

    fun getNextAvailableSpot(vehicleType: VehicleType): Spot? {
        for (spot in 1..totalSpots) {
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