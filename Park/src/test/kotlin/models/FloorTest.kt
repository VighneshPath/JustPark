package models

import exceptions.SpotDoesNotExistException
import models.vehicles.Car
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FloorTest {
    private lateinit var floor: Floor

    @BeforeEach
    fun setUp() {
        floor = Floor(100L)
    }

    @DisplayName("should get 1st spot")
    @Test
    fun getFirstAvailableSpot() {
        val expectedSpot = Spot(1L)

        val actualSpot = floor.getNextAvailableSpot()!!

        assertEquals(expectedSpot.getSpotsNumber(), actualSpot.getSpotsNumber())
    }

    @DisplayName("should fill up all spots and then give null")
    @Test
    fun fillUpSpots() {
        val car = Car()
        for (index in 1..100L) {
            floor.setSpotTo(index, car)
        }
        val expectedSpot: Spot? = null

        val actualSpot = floor.getNextAvailableSpot()

        assertEquals(expectedSpot, actualSpot)
    }

    @DisplayName("should throw exception if setting to invalid spot")
    @Test
    fun setSpotToVehicleWithError() {
        val unknownSpot = -1L
        val car = Car()
        val expectedErrorMessage = "Given spot does not exist"

        assertThrows<SpotDoesNotExistException>(expectedErrorMessage) { floor.setSpotTo(unknownSpot, car) }
    }

    @DisplayName("should return false if vehicle is already parked in spot")
    @Test
    fun setSpotWithVehicleAlreadyPresent() {
        val someSpot = 1L
        val car = Car()
        floor.setSpotTo(someSpot, car)
        val expectedValue = false

        val actualValue = floor.setSpotTo(someSpot, car)

        assertEquals(expectedValue, actualValue)
    }
}