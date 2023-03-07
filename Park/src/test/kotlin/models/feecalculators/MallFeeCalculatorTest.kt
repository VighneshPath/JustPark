package models.feecalculators

import models.FeeCalculatorFactory
import models.LocationType.MALL
import models.VehicleType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MallFeeCalculatorTest{
    @Test
    @DisplayName("should pay 50 for parking a two wheeler for 5 hours")
    fun shouldParkTwoWheelerFor5Hours(){
        val feeCalculator = FeeCalculatorFactory.createFeeCalculator(MALL)
        val expectedPrice = 50L

        val actualPrice = feeCalculator.getFinalPrice(5, VehicleType.TWO_WHEELER)

        assertEquals(expectedPrice, actualPrice)
    }

    @Test
    @DisplayName("should pay 60 for parking a car for 3 hours")
    fun shouldParkCarFor16Hours(){
        val feeCalculator = FeeCalculatorFactory.createFeeCalculator(MALL)
        val expectedPrice = 60L

        val actualPrice = feeCalculator.getFinalPrice(3, VehicleType.CAR)

        assertEquals(expectedPrice, actualPrice)
    }

    @Test
    @DisplayName("should pay 500 for parking a Heavy Vehicle for 10 hours")
    fun shouldPay0ForHeavyVehicle(){
        val feeCalculator = FeeCalculatorFactory.createFeeCalculator(MALL)
        val expectedPrice = 500L

        val actualPrice = feeCalculator.getFinalPrice(10, VehicleType.HEAVY_VEHICLE)

        assertEquals(expectedPrice, actualPrice)
    }
}