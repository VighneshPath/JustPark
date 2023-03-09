package models.feecalculators

import models.FeeCalculatorFactory
import models.LocationType.MALL
import models.VehicleType.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MallFeeCalculatorTest {
    @Test
    fun `should pay 250 for parking a two wheeler for 5 hours`(){
        val feeCalculator = FeeCalculatorFactory.createFeeCalculator(MALL)
        val expectedPrice = 250L

        val actualPrice = feeCalculator.getFinalPrice(5, TWO_WHEELER)

        assertEquals(expectedPrice, actualPrice)
    }

    @Test
    fun `should pay 180 for parking a car for 3 hours`(){
        val feeCalculator = FeeCalculatorFactory.createFeeCalculator(MALL)
        val expectedPrice = 180L

        val actualPrice = feeCalculator.getFinalPrice(3, CAR)

        assertEquals(expectedPrice, actualPrice)
    }

    @Test
    fun `should pay 5000 for parking a Heavy Vehicle for 10 hours`(){
        val feeCalculator = FeeCalculatorFactory.createFeeCalculator(MALL)
        val expectedPrice = 5000L

        val actualPrice = feeCalculator.getFinalPrice(10, HEAVY_VEHICLE)

        assertEquals(expectedPrice, actualPrice)
    }
}