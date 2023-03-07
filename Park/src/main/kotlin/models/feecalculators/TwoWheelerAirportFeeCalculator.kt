package models.feecalculators

import constants.TWO_WHEELER_AIRPORT_FEE_PER_HOUR
import models.feemodels.HourlyFeeModel

class TwoWheelerAirportFeeCalculator: FeeCalculator() {
    override fun getFinalPrice(duration: Long): Long {
        return super.calculatePrice(HourlyFeeModel(), duration, TWO_WHEELER_AIRPORT_FEE_PER_HOUR)
    }
}