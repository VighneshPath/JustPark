package models

import models.VehicleType.CAR
import models.VehicleType.TWO_WHEELER
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FloorTrackerTest {
    @Test
    fun `should create a floor tracker and get an available floor`(){
        val vehicleTypeLimits = mapOf(CAR to 1)
        val floorTracker = FloorTracker(listOf(vehicleTypeLimits))
        val expectedFloor = Floor(1, vehicleTypeLimits)

        val actualFloor = floorTracker.getNextAvailableFloor(CAR)!!

        assertEquals(expectedFloor.getFloorNumber(), actualFloor.getFloorNumber())
    }

    @Test
    fun `should fill a floor and return the next available floor`(){
        val floorTracker = FloorTracker(listOf(mapOf(CAR to 1), mapOf(CAR to 1)))
        val floor = floorTracker.getNextAvailableFloor(CAR)!!
        val car = Vehicle(CAR)
        floorTracker.parkVehicleAt(floor.getFloorNumber(), floor.getNextAvailableSpot(CAR)!!.getSpotsNumber(), car)
        val expectedFloor = Floor(2, mapOf(CAR to 1))

        val actualFloor = floorTracker.getNextAvailableFloor(CAR)!!

        assertEquals(expectedFloor.getFloorNumber(), actualFloor.getFloorNumber())
    }

    @Test
    fun `should get a floor with correct type of vehicle`(){
        val floorTracker = FloorTracker(listOf(mapOf(CAR to 2), mapOf(TWO_WHEELER to 1)))
        val expectedFloor = Floor(2, mapOf(TWO_WHEELER to 1))

        val actualFloor = floorTracker.getNextAvailableFloor(TWO_WHEELER)!!

        assertEquals(expectedFloor.getFloorNumber(), actualFloor.getFloorNumber())
    }

    @Test
    fun `should return null if spot for given type doesn't exist`(){
        val floorTracker = FloorTracker(listOf(mapOf(CAR to 2)))

        assertNull(floorTracker.getNextAvailableFloor(TWO_WHEELER))
    }
}