package models.feecalculators

import models.VehicleType.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class StadiumFeeCalculatorTest{
    @Test
    @DisplayName("should pay 90 for parking a two wheeler for 5 hours")
    fun shouldParkTwoWheelerFor5Hours(){
        val feeCalculator = StadiumFeeCalculator()
        val expectedPrice = 90L

        val actualPrice = feeCalculator.getFinalPrice(5, TWO_WHEELER)

        assertEquals(expectedPrice, actualPrice)
    }

    @Test
    @DisplayName("should pay 60 for parking a car for 16 hours")
    fun shouldParkCarFor16Hours(){
        val feeCalculator = StadiumFeeCalculator()
        val expectedPrice = 980L

        val actualPrice = feeCalculator.getFinalPrice(16, CAR)

        assertEquals(expectedPrice, actualPrice)
    }

    @Test
    @DisplayName("should pay 0 for parking a HeavyVehicle")
    fun shouldPay0ForHeavyVehicle(){
        val feeCalculator = StadiumFeeCalculator()
        val expectedPrice = 0L

        val actualPrice = feeCalculator.getFinalPrice(16, HEAVY_VEHICLE)

        assertEquals(expectedPrice, actualPrice)
    }
}