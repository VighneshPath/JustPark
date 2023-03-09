package models.feecalculators

import models.FeeCalculatorFactory
import models.LocationType.STADIUM
import models.VehicleType.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StadiumFeeCalculatorTest {
    @Test
    fun `should pay 90 for parking a two wheeler for 5 hours`(){
        val feeCalculator = FeeCalculatorFactory.createFeeCalculator(STADIUM)
        val expectedPrice = 90L

        val actualPrice = feeCalculator.getFinalPrice(5, TWO_WHEELER)

        assertEquals(expectedPrice, actualPrice)
    }

    @Test
    fun `should pay 60 for parking a car for 16 hours`(){
        val feeCalculator = FeeCalculatorFactory.createFeeCalculator(STADIUM)
        val expectedPrice = 980L

        val actualPrice = feeCalculator.getFinalPrice(16, CAR)

        assertEquals(expectedPrice, actualPrice)
    }

    @Test
    fun `should pay 0 for parking a HeavyVehicle`(){
        val feeCalculator = FeeCalculatorFactory.createFeeCalculator(STADIUM)
        val expectedPrice = 0L

        val actualPrice = feeCalculator.getFinalPrice(16, HEAVY_VEHICLE)

        assertEquals(expectedPrice, actualPrice)
    }
}