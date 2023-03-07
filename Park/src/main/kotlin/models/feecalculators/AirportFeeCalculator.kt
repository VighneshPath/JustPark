package models.feecalculators

import constants.CAR_AIRPORT_FEE_PER_HOUR
import models.VehicleType
import models.feemodels.HourlyFeeModel

class AirportFeeCalculator: FeeCalculator(){
    override fun getFinalPrice(duration: Long, vehicleType: VehicleType): Long {
        val feePerHour = AirportFactory.getRate(vehicleType)
        return super.calculatePrice(HourlyFeeModel(), duration, feePerHour)
    }
}