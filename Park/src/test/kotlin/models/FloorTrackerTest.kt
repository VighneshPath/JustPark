package models

import models.VehicleType.CAR
import models.VehicleType.TWO_WHEELER
import models.vehicles.Car
import models.vehicles.Vehicle
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FloorTrackerTest{
    @Test
    @DisplayName("should create a floor tracker and get an available floor")
    fun getAvailableFloor(){
        val vehicleTypeLimits = mapOf(CAR to 1)
        val floorTracker = FloorTracker(listOf(1), vehicleTypeLimits)
        val expectedFloor = Floor(1, 1)

        val actualFloor = floorTracker.getNextAvailableFloor(CAR)!!

        assertEquals(expectedFloor.getFloorNumber(), actualFloor.getFloorNumber())
    }

    @Test
    @DisplayName("should fill a floor and return the next available floor")
    fun getNextAvailableFloor(){
        val floorTracker = FloorTracker(listOf(1, 1), mapOf(CAR to 1))
        val floor = floorTracker.getNextAvailableFloor(CAR)!!
        val car: Vehicle = Car()
        floorTracker.parkVehicleAt(floor.getFloorNumber(), floor.getNextAvailableSpot(CAR)!!.getSpotsNumber(), car)
        val expectedFloor = Floor(2, 1)

        val actualFloor = floorTracker.getNextAvailableFloor(CAR)!!

        assertEquals(expectedFloor.getFloorNumber(), actualFloor.getFloorNumber())
    }

    @Test
    @DisplayName("should get a floor with correct type of vehicle")
    fun getNextAvailableFloorForTwoWheeler(){
        val floorTracker = FloorTracker(listOf(2, 1), mapOf(CAR to 2, TWO_WHEELER to 1))
        val expectedFloor = Floor(2, 1)

        val actualFloor = floorTracker.getNextAvailableFloor(TWO_WHEELER)!!

        assertEquals(expectedFloor.getFloorNumber(), actualFloor.getFloorNumber())
    }

    @Test
    @DisplayName("should return null if spot for given type doesn't exist")
    fun getSpotForVehicleWhichCannotPark(){
        val floorTracker = FloorTracker(listOf(2, 1), mapOf(CAR to 2))
        val expectedFloor = null

        val actualFloor = floorTracker.getNextAvailableFloor(TWO_WHEELER)!!

        assertEquals(expectedFloor, actualFloor.getFloorNumber())
    }
}