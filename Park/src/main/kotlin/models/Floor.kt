package models

import constants.VEHICLE_SPOT_LIMIT
import exceptions.SpotDoesNotExistException
import models.vehicles.Vehicle

class Floor(private val floorNumber: Int, private val totalSpots: Int = VEHICLE_SPOT_LIMIT) {
    private var spots: MutableList<Spot> = mutableListOf()

    init {
        for (spot in 0..totalSpots) {
            spots.add(Spot(spot))
        }
    }

    fun getFloorNumber() = floorNumber

    fun isFull() = getNextAvailableSpot() == null

    fun getNextAvailableSpot(): Spot? {
        for (spot in 1..totalSpots) {
            if (!spots[spot].isSpotTaken()) return spots[spot]
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