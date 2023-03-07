package models.feecalculators

import models.FeeCalculatorFactory
import models.LocationType.AIRPORT
import models.VehicleType
import models.VehicleType.TWO_WHEELER
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AirportFeeCalculatorTest{
    @Test
    @DisplayName("should pay 60 for parking a two wheeler for 9 hours")
    fun shouldParkTwoWheelerFor5Hours(){
        val feeCalculator = FeeCalculatorFactory.createFeeCalculator(AIRPORT)
        val expectedPrice = 60L

        val actualPrice = feeCalculator.getFinalPrice(9, TWO_WHEELER)

        assertEquals(expectedPrice, actualPrice)
    }

    @Test
    @DisplayName("should pay 160 for parking a car for 72 hours")
    fun shouldParkCarFor72Hours(){
        val feeCalculator = FeeCalculatorFactory.createFeeCalculator(AIRPORT)
        val expectedPrice = 200L

        val actualPrice = feeCalculator.getFinalPrice(72, VehicleType.CAR)

        assertEquals(expectedPrice, actualPrice)
    }

    @Test
    @DisplayName("should pay 0 for parking a HeavyVehicle")
    fun shouldPay0ForHeavyVehicle(){
        val feeCalculator = FeeCalculatorFactory.createFeeCalculator(AIRPORT)
        val expectedPrice = 0L

        val actualPrice = feeCalculator.getFinalPrice(16, VehicleType.HEAVY_VEHICLE)

        assertEquals(expectedPrice, actualPrice)
    }
}