package test.models

import main.models.Vehicle
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


class VehicleTest{
    @DisplayName("should park a vehicle in a spot")
    @Test
    fun parkVehicleInASpot(){
        val car = Vehicle()
        val expectedParkingStatus = true

        car.park()

        assertEquals(expectedParkingStatus, car.isParked())
    }
}