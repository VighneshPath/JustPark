package models

import exceptions.FloorDoesNotExistException
import models.vehicles.Vehicle

class FloorTracker(floorSizes: List<Int>) {
    private var floors: MutableList<Floor> = mutableListOf()
    init{
        floors.add(Floor(0, 0))
        for (index in 1..floorSizes.size) {
            floors.add(Floor(index, floorSizes[index - 1]))
        }
    }

    fun getNextAvailableFloor(): Floor? {
        for (index in 1 until floors.size) {
            if (!floors[index].isFull()) return floors[index]
        }

        return null
    }

    private fun checkFloor(floorNumber: Int){
        if (floorNumber >= floors.size || floorNumber <= 0) {
            throw FloorDoesNotExistException()
        }
    }

    fun getFloor(floorNumber: Int): Floor{
        checkFloor(floorNumber)
        return floors[floorNumber]
    }

    fun parkVehicleAt(floorNumber: Int, spotNumber: Int, vehicle: Vehicle){
        val floor = getFloor(floorNumber)
        floor.setSpotTo(spotNumber, vehicle)
    }

    fun unparkVehicleFrom(floorNumber: Int, spotNumber: Int){
        val floor = getFloor(floorNumber)
        floor.clearSpot(spotNumber)
    }

}