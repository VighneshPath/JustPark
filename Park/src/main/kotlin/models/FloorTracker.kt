package models

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

    fun getSize(): Int{
        return floors.size
    }

    fun getFloor(floorNumber: Int): Floor{
        return floors[floorNumber]
    }
}