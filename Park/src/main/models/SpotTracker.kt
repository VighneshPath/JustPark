package main.models

import main.exceptions.SpotDoesNotExistException
import main.models.vehicles.Vehicle

class SpotTracker(totalSpots: Long) {
    private var spots: MutableMap<Long, Spot> = mutableMapOf()

    init{
        for(spot in 0 until totalSpots){
            spots[spot] = Spot(spot)
        }
    }

    fun getNextAvailableSpot(): Spot?{
        spots.forEach{
            if(!it.value.isSpotTaken()) return it.value
        }
        return null
    }

    private fun isSpotTaken(spotNumber: Long): Boolean {
        if(spotNumber >= spots.size || spotNumber < 0){
            throw SpotDoesNotExistException()
        }
        return spots[spotNumber]!!.isSpotTaken()
    }

    fun setSpotTo(spotNumber: Long, vehicle: Vehicle): Boolean{
        if(!isSpotTaken(spotNumber)){
            spots[spotNumber]!!.reserveSpot(vehicle)
            return true
        }
        return false
    }

    fun clearSpot(spotNumber: Long): Boolean{
        spots[spotNumber]!!.unreserveSpot()
        return true
    }
}