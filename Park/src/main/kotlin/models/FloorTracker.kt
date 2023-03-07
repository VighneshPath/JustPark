package models

import exceptions.FloorDoesNotExistException

class FloorTracker(floorWiseTypeLimit: List<Map<VehicleType, Int>>) {
    private var floors: MutableList<Floor> = mutableListOf()
    init{
        floors.add(Floor(0, mapOf(VehicleType.CAR to 0)))
        floorWiseTypeLimit.forEach{
            floors.add(Floor(floors.size, it))
        }
    }

    fun getNextAvailableFloor(vehicleType: VehicleType): Floor? {
        for (index in 1 until floors.size) {
            if (!floors[index].isFull(vehicleType)) return floors[index]
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