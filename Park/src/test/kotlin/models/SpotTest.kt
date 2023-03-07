package models

import models.VehicleType.CAR
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SpotTest {
    private lateinit var spot: Spot

    @BeforeEach
    fun setUp() {
        spot = Spot(1, CAR)
    }

    @DisplayName("reserve a spot")
    @Test
    fun reserveASpot() {
        val car = Vehicle(CAR)
        val expectedSpotStatus = true

        spot.reserveSpot(car)

        assertEquals(expectedSpotStatus, spot.isSpotTaken())
        assertEquals(1, spot.getSpotsNumber())
    }

    @DisplayName("unreserve a spot")
    @Test
    fun unreserveASpot() {
        val car = Vehicle(CAR)
        val expectedSpotStatus = false
        spot.reserveSpot(car)

        spot.unreserveSpot()

        assertEquals(expectedSpotStatus, spot.isSpotTaken())
        assertEquals(1, spot.getSpotsNumber())
    }
}