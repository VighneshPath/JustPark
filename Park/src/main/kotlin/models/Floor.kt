package models

import constants.VEHICLE_SPOT_LIMIT
import exceptions.SpotDoesNotExistException
import models.vehicles.Vehicle

class Floor(private val totalSpots: Long = VEHICLE_SPOT_LIMIT, private val floorNumber: Int = 1) {
    private var spots: MutableMap<Long, Spot> = mutableMapOf()

    init {
        for (spot in 0..totalSpots) {
            spots[spot] = Spot(spot)
        }
    }

    fun getFloorNumber(): Int {
        return floorNumber
    }

    fun getNextAvailableSpot(): Spot? {
        for (spot in 1..totalSpots) {
            if (!spots[spot]!!.isSpotTaken()) return spots[spot]
        }
        return null
    }

    private fun isSpotTaken(spotNumber: Long): Boolean {
        if (spotNumber >= spots.size || spotNumber <= 0) {
            throw SpotDoesNotExistException()
        }
        return spots[spotNumber]!!.isSpotTaken()
    }

    fun setSpotTo(spotNumber: Long, vehicle: Vehicle): Boolean{
        if(!isSpotTaken(spotNumber)) {
            if (spots[spotNumber]!!.reserveSpot(vehicle)) {
                return true
            }
        }
        return false
    }

    fun clearSpot(spotNumber: Long): Boolean {
        if (spots[spotNumber]!!.unreserveSpot()) {
            return true
        }
        return false
    }
}