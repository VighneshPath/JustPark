package main.models

import main.constants.VEHICLE_SPOT_LIMIT
import main.exceptions.SpotDoesNotExistException
import main.models.vehicles.Vehicle

class Floor(private val totalSpots: Long = VEHICLE_SPOT_LIMIT) {
    private var spots: MutableMap<Long, Spot> = mutableMapOf()

    init {
        for (spot in 0..totalSpots) {
            spots[spot] = Spot(spot)
        }
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