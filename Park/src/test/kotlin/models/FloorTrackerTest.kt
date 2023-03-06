package models

import models.vehicles.Car
import models.vehicles.Vehicle
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FloorTrackerTest{
    @Test
    @DisplayName("should create a floor tracker and get an available floor")
    fun getAvailableFloor(){
        val floorTracker = FloorTracker(listOf(1))
        val expectedFloor = Floor(1, 1)

        val actualFloor = floorTracker.getNextAvailableFloor()!!

        assertEquals(expectedFloor.getFloorNumber(), actualFloor.getFloorNumber())
    }

    @Test
    @DisplayName("should fill a floor and return the next available floor")
    fun getNextAvailableFloor(){
        val floorTracker = FloorTracker(listOf(1, 1))
        val floor = floorTracker.getNextAvailableFloor()!!
        val car: Vehicle = Car()
        floorTracker.parkVehicleAt(floor.getFloorNumber(), floor.getNextAvailableSpot()!!.getSpotsNumber(), car)
        val expectedFloor = Floor(2, 1)

        val actualFloor = floorTracker.getNextAvailableFloor()!!

        assertEquals(expectedFloor.getFloorNumber(), actualFloor.getFloorNumber())
    }
}