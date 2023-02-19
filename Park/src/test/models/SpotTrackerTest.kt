package test.models

import main.exceptions.SpotDoesNotExistException
import main.models.Spot
import main.models.SpotTracker
import main.models.vehicles.Car
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SpotTrackerTest{
    private lateinit var spotTracker: SpotTracker
    @BeforeEach
    fun setUp(){
        spotTracker = SpotTracker(100L)
    }
    @DisplayName("should get 1st spot")
    @Test
    fun getFirstAvailableSpot(){
        val expectedSpot = Spot(1L)

        val actualSpot = spotTracker.getNextAvailableSpot()

        assertEquals(expectedSpot, actualSpot)
    }

    @DisplayName("should fill up all spots and then give null")
    @Test
    fun fillUpSpots(){
        val car = Car()
        for(index in 1 .. 100L){
            spotTracker.setSpotTo(index, car)
        }
        val expectedSpot: Spot? = null

        val actualSpot = spotTracker.getNextAvailableSpot()

        assertEquals(expectedSpot, actualSpot)
    }

    @DisplayName("should throw exception if setting to invalid spot")
    @Test
    fun setSpotToVehicleWithError(){
        val unknownSpot = -1L
        val car = Car()
        val expectedErrorMessage = "Given spot does not exist"
        assertThrows<SpotDoesNotExistException>(expectedErrorMessage){spotTracker.setSpotTo(unknownSpot, car)}
    }
}