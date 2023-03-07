package models

import models.LocationType.*
import models.feecalculators.AirportFeeCalculator
import models.feecalculators.FeeCalculator
import models.feecalculators.MallFeeCalculator
import models.feecalculators.StadiumFeeCalculator

object FeeCalculatorFactory {
    fun createFeeCalculator(locationType: LocationType): FeeCalculator {
        return when(locationType){
            AIRPORT-> AirportFeeCalculator()
            MALL -> MallFeeCalculator()
            STADIUM -> StadiumFeeCalculator()
        }
    }
}