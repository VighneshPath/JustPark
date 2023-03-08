package models

import models.LocationType.*
import models.feecalculators.AirportFeeCalculator
import models.feecalculators.FeeCalculator
import models.feecalculators.MallFeeCalculator
import models.feecalculators.StadiumFeeCalculator
import models.feedata.AirportFeeData
import models.feedata.MallFeeData
import models.feedata.StadiumFeeData

object FeeCalculatorFactory {
    fun createFeeCalculator(locationType: LocationType): FeeCalculator {
        return when (locationType) {
            AIRPORT -> AirportFeeCalculator(AirportFeeData())
            MALL -> MallFeeCalculator(MallFeeData())
            STADIUM -> StadiumFeeCalculator(StadiumFeeData())
        }
    }
}