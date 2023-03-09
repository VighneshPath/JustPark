package models

import exceptions.SpotDoesNotExistException
import models.VehicleType.CAR
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FloorTest {
    private var floor = Floor(100, mapOf(CAR to 100))

    @BeforeEach
    fun setUp() {
        floor = Floor(100, mapOf(CAR to 100))
    }

    @Test
    fun `should get 1st spot`(){
        val expectedSpot = Spot(1, CAR)

        val actualSpot = floor.getNextAvailableSpot(CAR)!!

        assertEquals(expectedSpot.getSpotsNumber(), actualSpot.getSpotsNumber())
    }

    @Test
    fun `should fill up all spots and then give null`(){
        val car = Vehicle(CAR)
        for (index in 1..100) {
            floor.setSpotTo(index, car)
        }
        val expectedSpot: Spot? = null

        val actualSpot = floor.getNextAvailableSpot(CAR)

        assertEquals(expectedSpot, actualSpot)
    }

    @Test
    fun `should throw exception if setting to invalid spot`(){
        val unknownSpot = -1
        val car = Vehicle(CAR)
        val expectedErrorMessage = "Given spot does not exist"

        assertThrows<SpotDoesNotExistException>(expectedErrorMessage) { floor.setSpotTo(unknownSpot, car) }
    }

    @Test
    fun `should return false if vehicle is already parked in spot`(){
        val someSpot = 1
        val car = Vehicle(CAR)
        floor.setSpotTo(someSpot, car)
        val expectedValue = false

        val actualValue = floor.setSpotTo(someSpot, car)

        assertEquals(expectedValue, actualValue)
    }
}