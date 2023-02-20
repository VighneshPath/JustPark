package models

import models.vehicles.Car
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SpotTest {
    private lateinit var spot: Spot

    @BeforeEach
    fun setUp() {
        spot = Spot(1L)
    }

    @DisplayName("reserve a spot")
    @Test
    fun reserveASpot() {
        val car = Car()
        val expectedSpotStatus = true

        spot.reserveSpot(car)

        assertEquals(expectedSpotStatus, spot.isSpotTaken())
        assertEquals(1L, spot.getSpotsNumber())
    }

    @DisplayName("unreserve a spot")
    @Test
    fun unreserveASpot() {
        val car = Car()
        val expectedSpotStatus = false
        spot.reserveSpot(car)

        spot.unreserveSpot()

        assertEquals(expectedSpotStatus, spot.isSpotTaken())
        assertEquals(1L, spot.getSpotsNumber())
    }
}