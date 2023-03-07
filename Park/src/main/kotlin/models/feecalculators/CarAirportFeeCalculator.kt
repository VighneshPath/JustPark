package models.feecalculators

import constants.CAR_AIRPORT_FEE_PER_HOUR
import models.feemodels.HourlyFeeModel

class CarAirportFeeCalculator : FeeCalculator() {
    override fun getFinalPrice(duration: Long): Long {
        return super.calculatePrice(HourlyFeeModel(), duration, CAR_AIRPORT_FEE_PER_HOUR)
    }
}